/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view.config;

import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.controller.ExtConfigurationObjectController;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.ConfigurationObjectConfig;
import com.dot.gcpbasedot.enums.PageType;
import com.lacv.marketplatform.dtos.config.ContactConfigDto;
import com.lacv.marketplatform.dtos.config.PortalConfigDto;
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
@RequestMapping(value="/vista/generalConfig")
public class GeneralViewConfigController extends ExtConfigurationObjectController {
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ConfigurationObjectConfig viewConfig= new ConfigurationObjectConfig("generalConfig");
        viewConfig.setMainConfigurationTitle("Gestionar Configuraci&oacute;n General");
        viewConfig.addControlConfigurationObjectView("portalConfig", "Configuraci&oacute;n del Portal", PortalConfigDto.class);
        viewConfig.addControlConfigurationObjectView("contactConfig", "Configuraci&oacute;n de Contacto", ContactConfigDto.class);
        
        super.addControlMapping(viewConfig);
        
        MenuItem menuItem= new MenuItem("Configuraci&oacute;n", "generalConfig", "Gestionar Configuraci&oacute;n General");
        menuItem.setPageType(PageType.CONFIGURATION_OBJECT);
        menuComponent.addItemMenu(menuItem);
        
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
    
}
