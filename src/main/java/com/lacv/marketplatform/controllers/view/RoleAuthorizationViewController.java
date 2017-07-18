/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.RoleAuthorizationDto;
import com.lacv.marketplatform.mappers.RoleAuthorizationMapper;
import com.lacv.marketplatform.services.RoleAuthorizationService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
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
@RequestMapping(value="/vista/roleAuthorization")
public class RoleAuthorizationViewController extends ExtEntityController {
    
    @Autowired
    RoleAuthorizationService roleAuthorizationService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    RoleAuthorizationMapper roleAuthorizationMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("roleAuthorization", "id", roleAuthorizationService, RoleAuthorizationDto.class);
        view.setSingularEntityTitle("Comercio");
        view.setPluralEntityTitle("Comercios");
        view.activateNNMulticheckChild("authorization");
        super.addControlMapping(view);
        
        /*MenuItem menuItem= new MenuItem("Seguridad", "roleAuthorization", "Gestionar Comercios");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);*/
    }
    
    
}
