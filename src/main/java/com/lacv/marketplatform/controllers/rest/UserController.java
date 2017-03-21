/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.mappers.UserMapper;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.WebFile;
import com.lacv.marketplatform.services.WebFileService;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/user")
public class UserController extends RestController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;
    
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
        WebFile webParentFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" +fileName.replaceAll(" ", "_");
            User user = userService.findById(idParent);
            user.setUrlPhoto(WebConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + path + imageName);
            userService.update(user);
            
            webFileService.createByFileData(webParentFile, slice, imageName, fileType, fileSize, is);
            
            return "Archivo " + imageName + " almacenado correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @Override
    public String saveResizedImage(String fileName, String fileType, int width, int height, int fileSize, InputStream is, Object idParent){
        String path= "imagenes/usuario/";
        WebFile webParentFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" + width + "x" + height + "_" +fileName.replaceAll(" ", "_");
            
            webFileService.createByFileData(webParentFile, 0, imageName, fileType, fileSize, is);
            
            return "Archivo " + imageName + " almacenado correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
}
