/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.TableColumnDto;
import com.lacv.marketplatform.mappers.TableColumnMapper;
import com.lacv.marketplatform.services.TableColumnService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
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
@RequestMapping(value="/vista/tableColumn")
public class TableColumnViewController extends ExtViewController {
    
    @Autowired
    TableColumnService tableColumnService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    TableColumnMapper tableColumnMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("tableColumn", "name", tableColumnService, TableColumnDto.class);
        view.setSingularEntityTitle("Columna de la tabla");
        view.setPluralEntityTitle("Columnas de la tabla");
        super.addControlMapping(view);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
}
