/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.mappers.ProductMapper;
import com.lacv.marketplatform.services.ProductService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.lacv.marketplatform.entities.ProductImage;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/product")
public class ProductViewController extends ExtViewController {
    
    @Autowired
    ProductService productService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    ProductMapper productMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("product", "name", productService, ProductDto.class);
        view.setSingularEntityTitle("Producto");
        view.setPluralEntityTitle("Productos");
        view.addChildExtView("productImage", ProductImage.class, ViewConfig.TCV_STANDARD);
        view.addComboboxChildDependent("category", "subCategory");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Productos", "product", "Gestionar Productos");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
