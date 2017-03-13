/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.ProductImageMapper;
import com.lacv.marketplatform.services.ProductImageService;
import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.entities.ProductImage;
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
@RequestMapping(value="/rest/productImage")
public class ProductImageController extends RestController {
    
    @Autowired
    ProductImageService productImageService;
    
    @Autowired
    ProductImageMapper productImageMapper;
    
    @Autowired
    WebFileService webFileService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("productImage", productImageService, productImageMapper);
    }
    
    @Override
    public String saveFilePart(int slice, String fileName, String fileType, int fileSize, InputStream is, Object idParent) {
        String path= "imagenes/producto/";
        WebFile webParentFile= webFileService.findByPath(path);
        
        try {
            String imageName= idParent + "_" +fileName.replaceAll(" ", "_");
            ProductImage productImage = productImageService.findById(idParent);
            productImage.setImage(WebConstants.LOCAL_DOMAIN + WebConstants.ROOT_FOLDER + path + imageName);
            productImageService.update(productImage);
            
            webFileService.createByFileData(webParentFile, slice, imageName, fileType, fileSize, is);
            
            return "Archivo " + fileName + " almacenado correctamente con ID " + idParent;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    
}
