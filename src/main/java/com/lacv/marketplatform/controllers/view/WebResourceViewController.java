/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.WebResourceDto;
import com.lacv.marketplatform.mappers.WebResourceMapper;
import com.lacv.marketplatform.services.WebResourceService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.lacv.marketplatform.entities.WebresourceAuthorization;
import com.lacv.marketplatform.entities.WebresourceRole;
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
@RequestMapping(value="/vista/webResource")
public class WebResourceViewController extends ExtViewController {
    
    @Autowired
    WebResourceService webResourceService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    WebResourceMapper webResourceMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("webResource", "name", webResourceService, WebResourceDto.class);
        view.setSingularEntityTitle("Recurso Web");
        view.setPluralEntityTitle("Recursos Web");
        view.addChildExtView("webresourceRole", WebresourceRole.class, ViewConfig.TCV_N_N_MULTICHECK);
        view.addChildExtView("webresourceAuthorization", WebresourceAuthorization.class, ViewConfig.TCV_N_N_MULTICHECK);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Seguridad", "webResource", "Gestionar Recursos Web");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
