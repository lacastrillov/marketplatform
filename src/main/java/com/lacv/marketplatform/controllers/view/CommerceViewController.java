/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.CommerceDto;
import com.lacv.marketplatform.mappers.CommerceMapper;
import com.lacv.marketplatform.services.CommerceService;
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
@RequestMapping(value="/vista/commerce")
public class CommerceViewController extends ExtEntityController {
    
    @Autowired
    CommerceService commerceService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    CommerceMapper commerceMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("commerce", "commerceName", commerceService, CommerceDto.class);
        view.setSingularEntityTitle("Comercio");
        view.setPluralEntityTitle("Comercios");
        view.setMultipartFormData(true);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Comercios", "commerce", "Gestionar Comercios");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
