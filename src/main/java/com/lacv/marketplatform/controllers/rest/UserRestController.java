/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.mappers.UserMapper;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.WebFile;
import com.lacv.marketplatform.services.WebFileService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/user")
public class UserRestController extends RestSessionController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    WebFileService webFileService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("user", userService, userMapper);
        super.setDtoClass(UserDto.class);
    }
    
    @Override
    public String saveFilePart(int slice, String fileName, String fileType, int fileSize, InputStream is, Object idParent) {
        String path= "imagenes/usuario/";
        WebFile parentWebFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" +fileName.replaceAll(" ", "_");
            User user = userService.findById(idParent);
            user.setUrlPhoto(WebConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + path + imageName);
            userService.update(user);
            
            webFileService.createByFileData(parentWebFile, slice, imageName, fileType, fileSize, is);
            
            return "Archivo " + imageName + " almacenado correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @Override
    public String saveResizedImage(String fileName, String fileType, int width, int height, int fileSize, InputStream is, Object idParent){
        String path= "imagenes/usuario/";
        WebFile parentWebFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" + width + "x" + height + "_" +fileName.replaceAll(" ", "_");
            
            webFileService.createByFileData(parentWebFile, 0, imageName, fileType, fileSize, is);
            
            return "Archivo " + imageName + " almacenado correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters) {
        jsonFilters.getJSONObject("eq").put("id", securityService.getCurrentUser().getId());
        return jsonFilters;
    }

    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }
    
    @Override
    public boolean canLoad(BaseEntity entity){
        User user= (User) entity;
        return securityService.getCurrentUser().getId().equals(user.getId());
    }
    
    @Override
    public boolean canCreate(BaseEntity entity){
        return false;
    }
    
    @Override
    public boolean canUpdate(BaseEntity entity){
        User user= (User) entity;
        return securityService.getCurrentUser().getId().equals(user.getId());
    }

    @Override
    public boolean canDelete(BaseEntity entity) {
        return false;
    }
    
}
