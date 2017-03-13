/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.SubCategoryDto;
import com.lacv.marketplatform.mappers.SubCategoryMapper;
import com.lacv.marketplatform.services.SubCategoryService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/subCategory")
public class SubCategoryViewController extends ExtViewController {
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SubCategoryMapper subCategoryMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("subCategory", "name", subCategoryService, SubCategoryDto.class);
        view.setSingularEntityTitle("Sub Categoria");
        view.setPluralEntityTitle("Sub Categorias");
        view.setMultipartFormData(true);
        super.addControlMapping(view);
    }
    
    
}
