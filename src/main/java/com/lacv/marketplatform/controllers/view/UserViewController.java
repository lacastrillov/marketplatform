/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.mappers.UserMapper;
import com.lacv.marketplatform.services.UserService;
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
@RequestMapping(value="/vista/user")
public class UserViewController extends ExtViewController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    UserMapper userMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("user", "name", userService, UserDto.class);
        view.setSingularEntityTitle("Usuario");
        view.setPluralEntityTitle("Usuarios");
        view.addChildExtView("userRole", UserRole.class, ViewConfig.TCV_N_N_MULTICHECK);
        view.setMultipartFormData(true);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "user", "Gestionar Usuarios");
        menuItem.setItemPosition(2);
        menuComponent.addItemMenu(menuItem);
        
        super.addMenuComponent(menuComponent);
    }
    
    
}
