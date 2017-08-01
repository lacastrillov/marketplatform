/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.AuthorizationDto;
import com.lacv.marketplatform.mappers.AuthorizationMapper;
import com.lacv.marketplatform.services.AuthorizationService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.EntityConfig;
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
@RequestMapping(value="/vista/authorization")
public class AuthorizationViewController extends ExtEntityController {
    
    @Autowired
    AuthorizationService authorizationService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    AuthorizationMapper authorizationMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("authorization", "name", authorizationService, AuthorizationDto.class);
        view.setSingularEntityTitle("Autorizaci&oacute;n");
        view.setPluralEntityTitle("Autorizaciones");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "authorization", "Gestionar Autorizaciones");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
