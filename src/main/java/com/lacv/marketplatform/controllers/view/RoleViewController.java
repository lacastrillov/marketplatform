/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.RoleDto;
import com.lacv.marketplatform.mappers.RoleMapper;
import com.lacv.marketplatform.services.RoleService;
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
@RequestMapping(value="/vista/role")
public class RoleViewController extends ExtViewController {
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    RoleMapper roleMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("role", "name", roleService, RoleDto.class);
        view.setSingularEntityTitle("Rol");
        view.setPluralEntityTitle("Roles");
        view.addInternalViewButton("userRole", "Ver Usuarios");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "role", "Gestionar Roles");
        menuItem.setParentPosition(1);
        menuItem.setItemPosition(1);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
