/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.UserRoleDto;
import com.lacv.marketplatform.mappers.UserRoleMapper;
import com.lacv.marketplatform.services.UserRoleService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
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
@RequestMapping(value="/vista/userRole")
public class UserRoleViewController extends ExtEntityController {
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    UserRoleMapper userRoleMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("userRole", "id", userRoleService, UserRoleDto.class);
        view.setPluralEntityTitle("Roles de Usuario");
        view.setSingularEntityTitle("Roles de Usuario");
        view.activateNNMulticheckChild("role");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "userRole", "Gestionar Roles de Usuario");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
