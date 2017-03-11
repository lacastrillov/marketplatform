/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.WebFileDto;
import com.lacv.marketplatform.mappers.WebFileMapper;
import com.lacv.marketplatform.services.WebFileService;
import com.dot.gcpbasedot.controller.ExtFileExplorerController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.GridTemplate;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.FileExplorerConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/webFile")
public class WebFileViewController extends ExtFileExplorerController {
    
    @Autowired
    WebFileService webFileService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    WebFileMapper webFileMapper;
    
    
    @PostConstruct
    public void init(){
        FileExplorerConfig view= new FileExplorerConfig("webFile", "name", webFileService, WebFileDto.class);
        view.setSingularEntityTitle("Archivo Web");
        view.setPluralEntityTitle("Archivos Web");
        view.setMultipartFormData(true);
        
        GridTemplate gridTemplate= new GridTemplate("webFile.vm");
        gridTemplate.setNumColumns(6);
        view.setGridTemplate(gridTemplate);
        view.setActiveGridTemplateAsParent(true);
        
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Gestor de Contenidos", "webFile", "Explorador de Archivos");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
