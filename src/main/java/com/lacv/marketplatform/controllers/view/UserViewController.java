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
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ProcessButton;
import com.dot.gcpbasedot.dto.config.EntityConfig;
import com.lacv.marketplatform.dtos.process.CreatePasswordDto;
import com.lacv.marketplatform.dtos.process.MainLocationPDto;
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
@RequestMapping(value="/vista/user")
public class UserViewController extends ExtEntityController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("user", "firstName", userService, UserDto.class);
        view.setSingularEntityTitle("Usuario");
        view.setPluralEntityTitle("Usuarios");
        view.addChildExtView("userRole", UserRole.class, EntityConfig.TCV_N_N_MULTICHECK);
        view.setMultipartFormData(true);
        
        ProcessButton setPasswordButton= new ProcessButton();
        setPasswordButton.setMainProcessRef("processUser");
        setPasswordButton.setProcessName("createPassword");
        setPasswordButton.setProcessTitle("Crear Password");
        setPasswordButton.addSourceByDestinationField("username", "username");
        setPasswordButton.setDtoClass(CreatePasswordDto.class);
        setPasswordButton.setIconUrl("/img/process_icons/password.png");
        view.addProcessButton(setPasswordButton);
        
        ProcessButton mainLocationButton= new ProcessButton();
        mainLocationButton.setMainProcessRef("processMainLocation");
        mainLocationButton.setProcessName("crearMainLocation");
        mainLocationButton.setProcessTitle("Crear Main Location");
        mainLocationButton.addSourceByDestinationField("email", "usuario.correo");
        mainLocationButton.addSourceByDestinationField("name", "usuario.nombre");
        mainLocationButton.addSourceByDestinationField("status", "usuario.estado");
        mainLocationButton.setDtoClass(MainLocationPDto.class);
        mainLocationButton.setIconUrl("/img/process_icons/ml-process.png");
        view.addProcessButton(mainLocationButton);
        
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "user", "Gestionar Usuarios");
        menuItem.setItemPosition(2);
        menuComponent.addItemMenu(menuItem);
        
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
