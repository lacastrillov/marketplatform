/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view.process;

import com.lacv.marketplatform.dtos.LogProcessDto;
import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.lacv.marketplatform.dtos.process.CreatePasswordDto;
import com.dot.gcpbasedot.controller.ExtProcessController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ProcessConfig;
import com.lacv.marketplatform.dtos.process.ContactUserPDto;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/processUser")
public class UserViewProcessController extends ExtProcessController {
    
    @Autowired
    MenuComponent menuComponent;
    
    
    @PostConstruct
    public void init(){
        ProcessConfig process= new ProcessConfig("processUser", "logProcess", LogProcessDto.class);
        process.setMainProcessTitle("Gestionar Procesos de Usuario");
        process.addControlProcessView("createPassword", "Crear Password", CreatePasswordDto.class, BasicResultDto.class);
        process.addControlProcessView("contactUser", "Contacto de Usuario", ContactUserPDto.class, BasicResultDto.class);
        
        super.addControlMapping(process);
        
        MenuItem menuItem= new MenuItem("Procesos", "processUser", "Gestionar Procesos de Usuario");
        menuItem.setParentPosition(4);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
