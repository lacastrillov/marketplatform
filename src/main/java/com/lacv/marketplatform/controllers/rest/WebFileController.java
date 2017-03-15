/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.entities.WebFile;
import com.lacv.marketplatform.mappers.WebFileMapper;
import com.lacv.marketplatform.services.WebFileService;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.enums.WebFileType;
import com.dot.gcpbasedot.service.gcp.StorageService;
import com.dot.gcpbasedot.util.FileService;
import com.dot.gcpbasedot.util.Util;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/webFile")
public class WebFileController extends RestController {
    
    @Autowired
    WebFileService webFileService;
    
    @Autowired
    WebFileMapper webFileMapper;
    
    @Autowired
    StorageService storageService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("webFile", webFileService, webFileMapper);
    }
    
    @RequestMapping(value = "/create.htm")
    @ResponseBody
    @Override
    public byte[] create(@RequestParam String data, @RequestParam(required = false) String callback) {
        byte[] result= super.create(data, callback, entityRef);
        JSONObject jsonResult= new JSONObject(new String(result, StandardCharsets.UTF_8));
        
        WebFile webFile= webFileService.findById(jsonResult.getJSONObject("data").getLong("id"));
        String path= webFile.getPath();
        String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + path;
        if(webFile.getType().equals(WebFileType.folder.name())){
            FileService.createFolder(location + webFile.getName());
            
            webFile.setCreationDate(new Date());
            webFile.setIcon("folder");
            webFile.setModificationDate(new Date());
            webFile.setSize(1);
            
            webFileService.update(webFile);
        }else{
            FileService.createFile(location + webFile.getName());
            
            String extension= FilenameUtils.getExtension(webFile.getName());
            webFile.setCreationDate(new Date());
            webFile.setType(extension);
            webFile.setIcon(Util.getSimpleContentType(extension));
            webFile.setModificationDate(new Date());
            webFile.setSize(1);
            
            webFileService.update(webFile);
        }
        
        return result;
    }
    
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    @Override
    public byte[] update(@RequestParam String data, @RequestParam(required = false) String callback) {
        JSONObject jsonObject= new JSONObject(data);
        
        if(jsonObject.has("id") && jsonObject.has("name")){
            WebFile webFile= webFileService.findById(jsonObject.getLong("id"));
            if(!webFile.getName().equals(jsonObject.getString("name"))){
                String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + webFile.getPath();
                FileService.renameFile(location + webFile.getName(), location + jsonObject.getString("name"));
            }
        }
        
        return update(data, callback, entityRef);
    }
    
    @RequestMapping(value = "/delete/byfilter.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Override
    public String deleteByFilter(@RequestParam String filter, @RequestParam(required = false) String callback) {
        String result= super.deleteByFilter(filter, callback, entityRef);
        JSONObject jsonResult= new JSONObject(result);
        if(jsonResult.getBoolean("success")){
            JSONArray webFiles= jsonResult.getJSONArray("data");
            for(int i=0; i<webFiles.length(); i++){
                JSONObject webFile= webFiles.getJSONObject(i);
                String path= (webFile.has("path"))?webFile.getString("path"):"";
                LOGGER.info("path: "+path);
                String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + path;
                
                FileService.deleteFile(location + webFile.getString("name"));
            }
        }
        
        return result;
    }
    
    @Override
    public String saveFilePart(int slice, String fileName, String fileType, int fileSize, InputStream filePart, Object idParent){
        try {
            WebFile webParentFile= null;
            if(!idParent.toString().equals("undefined")){
                webParentFile = webFileService.findById(new Long(idParent.toString()));
            }
            webFileService.createByFileData(webParentFile, slice, fileName, fileType, fileSize, filePart);
            
            return "Archivo " + fileName + " almacenado correctamente";
        } catch (Exception ex) {
            LOGGER.error("saveFile ", ex);
            return ex.getMessage();
        }
    }
    
}
