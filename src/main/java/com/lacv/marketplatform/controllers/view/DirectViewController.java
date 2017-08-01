/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.controller.ExtTableController;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.TableConfig;
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
@RequestMapping(value="/vista/direct")
public class DirectViewController extends ExtTableController {
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        TableConfig view= new TableConfig("direct");
        super.addControlMapping(view);
        /*MenuItem menuItem= new MenuItem("Pedidos", "inventoryOrder", "Gestionar Ordenes de Inventario");
        menuComponent.addItemMenu(menuItem);*/
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
    
}
