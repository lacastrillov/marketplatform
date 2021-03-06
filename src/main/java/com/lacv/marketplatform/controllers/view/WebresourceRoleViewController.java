/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.WebresourceRoleDto;
import com.lacv.marketplatform.mappers.WebresourceRoleMapper;
import com.lacv.marketplatform.services.WebresourceRoleService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.EntityConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/webresourceRole")
public class WebresourceRoleViewController extends ExtEntityController {
    
    @Autowired
    WebresourceRoleService webresourceRoleService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    WebresourceRoleMapper webresourceRoleMapper;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("webresourceRole", "id", webresourceRoleService, WebresourceRoleDto.class);
        view.setSingularEntityTitle("Rol");
        view.setPluralEntityTitle("Roles");
        view.activateNNMulticheckChild("role");
        super.addControlMapping(view);
        
        /*MenuItem menuItem= new MenuItem("Seguridad", "webresourceRole", "Gestionar Comercios");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);*/
    }
    
    
}
