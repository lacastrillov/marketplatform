/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.MailDto;
import com.lacv.marketplatform.mappers.MailMapper;
import com.lacv.marketplatform.services.MailService;
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
@RequestMapping(value="/vista/mail")
public class MailViewController extends ExtViewController {
    
    @Autowired
    MailService mailService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    MailMapper mailMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("mail", "id", mailService, MailDto.class);
        view.setSingularEntityTitle("Correo");
        view.setPluralEntityTitle("Correos");
        view.setEditableForm(false);
        view.setEditableGrid(false);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Correos", "mail", "Gestionar Correos");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
