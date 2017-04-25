/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.entities.SubCategory;
import com.lacv.marketplatform.mappers.SubCategoryMapper;
import com.lacv.marketplatform.services.SubCategoryService;
import com.lacv.marketplatform.constants.WebConstants;
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
@RequestMapping(value="/rest/subCategory")
public class SubCategoryController extends RestController {
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @Autowired
    SubCategoryMapper subCategoryMapper;
    
    @Autowired
    WebFileService webFileService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("subCategory", subCategoryService, subCategoryMapper);
    }
    
    @Override
    public String saveFilePart(int slice, String fileName, String fileType, int fileSize, InputStream is, Object idEntity) {
        String path= "imagenes/subcategoria/";
        WebFile parentWebFile= webFileService.findByPath(path);
        
        try {
            String imageName= idEntity + "_" +fileName.replaceAll(" ", "_");
            SubCategory subCategory = subCategoryService.findById(idEntity);
            subCategory.setImage(WebConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + path + imageName);
            subCategoryService.update(subCategory);
            
            webFileService.createByFileData(parentWebFile, slice, imageName, fileType, fileSize, is);
            
            return "Archivo " + fileName + " almacenado correctamente con ID " + idEntity;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
}
