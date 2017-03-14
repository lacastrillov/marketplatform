/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.MainLocationDto;
import com.lacv.marketplatform.mappers.MainLocationMapper;
import com.lacv.marketplatform.services.MainLocationService;
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
@RequestMapping(value="/vista/mainLocation")
public class MainLocationViewController extends ExtViewController {
    
    @Autowired
    MainLocationService mainLocationService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    MainLocationMapper mainLocationMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("mainLocation", "mlName", mainLocationService, MainLocationDto.class);
        view.setSingularEntityTitle("Ubicaci&oacute;n Principal");
        view.setPluralEntityTitle("Ubicaciones Principales");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Comercios", "mainLocation", "Gestionar Ubicaciones Principales");
        menuItem.setParentPosition(6);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
