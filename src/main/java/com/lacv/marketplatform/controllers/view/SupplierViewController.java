/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.SupplierDto;
import com.lacv.marketplatform.mappers.SupplierMapper;
import com.lacv.marketplatform.services.SupplierService;
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
@RequestMapping(value="/vista/supplier")
public class SupplierViewController extends ExtViewController {
    
    @Autowired
    SupplierService supplierService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SupplierMapper supplierMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("supplier", "companyName", supplierService, SupplierDto.class);
        view.setSingularEntityTitle("Proveedor");
        view.setPluralEntityTitle("Proveedores");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Pedidos", "supplier", "Gestionar Proveedores");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
