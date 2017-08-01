/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view.process;

import com.lacv.marketplatform.dtos.LogProcessDto;
import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.dot.gcpbasedot.controller.ExtProcessController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.ProcessConfig;
import com.dot.gcpbasedot.enums.PageType;
import com.lacv.marketplatform.dtos.process.ActivationProductPDto;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/processProduct")
public class ProductViewProcessController extends ExtProcessController {
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ProcessConfig process= new ProcessConfig("processProduct", "logProcess", LogProcessDto.class);
        process.setMainProcessTitle("Gestionar Procesos de Producto");
        process.addControlProcessView("activarProducto", "Activar Producto", ActivationProductPDto.class, BasicResultDto.class);
        
        super.addControlMapping(process);
        
        MenuItem menuItem= new MenuItem("Procesos", "processProduct", "Gestionar Procesos de Producto");
        menuItem.setPageType(PageType.PROCESS);
        menuComponent.addItemMenu(menuItem);
        
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
    
}
