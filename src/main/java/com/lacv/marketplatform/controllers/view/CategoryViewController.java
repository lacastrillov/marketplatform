/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.CategoryDto;
import com.lacv.marketplatform.mappers.CategoryMapper;
import com.lacv.marketplatform.services.CategoryService;
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
@RequestMapping(value="/vista/category")
public class CategoryViewController extends ExtViewController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    CategoryMapper categoryMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("category", "name", categoryService, CategoryDto.class);
        view.setSingularEntityTitle("Categoria");
        view.setPluralEntityTitle("Categorias");
        view.setMultipartFormData(true);
        view.addInternalViewButton("product", "Ver productos");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Productos", "category", "Gestionar Categorias");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
