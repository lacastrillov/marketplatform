/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestDirectController;
import com.dot.gcpbasedot.service.JdbcDirectService;
import com.lacv.marketplatform.components.WebConstants;
import com.lacv.marketplatform.entities.WebFile;
import com.lacv.marketplatform.services.WebFileService;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/direct")
public class DirectRestController extends RestDirectController {
    
    @Autowired
    JdbcDirectService jdbcDirectService;
    
    @Autowired
    WebFileService webFileService;
    
    @Autowired
    WebConstants webConstants;
    
    
    private WebFile getParentWebFile(String tableName){
        String folder= tableName.replaceFirst("lt_", "");
        String pathSup= "archivos/";
        String path= pathSup + folder + "/";
        WebFile parentWebFile= webFileService.findByPath(path);
        if(parentWebFile==null || !parentWebFile.getName().equals(folder)){
            WebFile webParentSupFile= webFileService.findByPath(pathSup);
            parentWebFile= webFileService.createFolder(webParentSupFile, folder);
        }
        return parentWebFile;
    }
    
    @Override
    public String saveFilePart(String tableName, String fieldName, String fileName, String fileType, int fileSize, InputStream is, Integer idEntity) {
        try {
            String newFileName= idEntity + "_" +fieldName+"."+FilenameUtils.getExtension(fileName);
            Map<String,Object> entity = jdbcDirectService.loadByParameter(tableName, "id", idEntity);
            WebFile parentWebFile= getParentWebFile(tableName);
            
            entity.put(fieldName, webConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + parentWebFile.getPath() + parentWebFile.getName() + "/" + newFileName);
            jdbcDirectService.updateByParameter(tableName, entity, "id", idEntity);
            
            webFileService.createByFileData(parentWebFile, 0, newFileName, fileType, fileSize, is);
            
            return "Archivo " + newFileName + " almacenado correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
}
