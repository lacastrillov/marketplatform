/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.LeadTableDto;
import com.lacv.marketplatform.mappers.LeadTableMapper;
import com.lacv.marketplatform.services.LeadTableService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.config.EntityConfig;
import com.lacv.marketplatform.entities.TableColumn;
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
@RequestMapping(value="/vista/leadTable")
public class LeadTableViewController extends ExtEntityController {
    
    @Autowired
    LeadTableService leadTableService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    LeadTableMapper leadTableMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("leadTable", "name", leadTableService, LeadTableDto.class);
        view.setSingularEntityTitle("Tabla Lead");
        view.setPluralEntityTitle("Tablas Lead");
        view.addChildExtView("tableColumn", TableColumn.class, EntityConfig.TCV_STANDARD);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Tablas Lead", "leadTable", "Gestionar Tablas Lead");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
