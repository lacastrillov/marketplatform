/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.WebresourceAuthorizationDto;
import com.lacv.marketplatform.mappers.WebresourceAuthorizationMapper;
import com.lacv.marketplatform.services.WebresourceAuthorizationService;
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
@RequestMapping(value="/vista/webresourceAuthorization")
public class WebresourceAuthorizationViewController extends ExtEntityController {
    
    @Autowired
    WebresourceAuthorizationService webresourceAuthorizationService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    WebresourceAuthorizationMapper webresourceAuthorizationMapper;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("webresourceAuthorization", "id", webresourceAuthorizationService, WebresourceAuthorizationDto.class);
        view.setSingularEntityTitle("Autorizaci&oacute;n");
        view.setPluralEntityTitle("Autorizaciones");
        view.activateNNMulticheckChild("authorization");
        super.addControlMapping(view);
        
        /*MenuItem menuItem= new MenuItem("Seguridad", "webresourceAuthorization", "Gestionar Comercios");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);*/
    }
    
    
}
