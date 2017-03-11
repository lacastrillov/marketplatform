/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.MailTemplateDto;
import com.lacv.marketplatform.mappers.MailTemplateMapper;
import com.lacv.marketplatform.services.MailTemplateService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
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
@RequestMapping(value="/vista/mailTemplate")
public class MailTemplateViewController extends ExtViewController {
    
    @Autowired
    MailTemplateService mailTemplateService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    MailTemplateMapper mailTemplateMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("mailTemplate", "alias", mailTemplateService, MailTemplateDto.class);
        view.setSingularEntityTitle("Plantilla de Correo");
        view.setPluralEntityTitle("Plantillas de Correo");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Correos", "mailTemplate", "Gestionar Plantillas de Correo");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
