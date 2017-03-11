/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.CategoryMapper;
import com.lacv.marketplatform.services.CategoryService;
import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.entities.Category;
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
@RequestMapping(value="/rest/category")
public class CategoryController extends RestController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryMapper categoryMapper;
    
    @Autowired
    WebFileService webFileService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("category", categoryService, categoryMapper);
    }
    
    @Override
    public String saveFilePart(int slice, String fileName, String fileType, int fileSize, InputStream is, Object idParent) {
        String path= "imagenes/categoria/";
        WebFile webParentFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" +fileName.replaceAll(" ", "_");
            Category category = categoryService.findById(idParent);
            category.setImage(WebConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + path + imageName);
            categoryService.update(category);
            
            webFileService.createByFileData(webParentFile, slice, imageName, fileType, fileSize, is);
            
            return "Archivo " + fileName + " almacenado correctamente con ID " + idParent;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    
}
