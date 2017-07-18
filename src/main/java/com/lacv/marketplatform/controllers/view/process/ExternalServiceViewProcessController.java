/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view.process;

import com.lacv.marketplatform.dtos.LogProcessDto;
import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.dot.gcpbasedot.controller.ExtProcessController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ProcessConfig;
import com.dot.gcpbasedot.enums.PageType;
import com.lacv.marketplatform.dtos.process.BasicPDto;
import com.lacv.marketplatform.dtos.process.NetworkPDto;
import com.lacv.marketplatform.dtos.process.ProductoPDto;
import com.lacv.marketplatform.dtos.process.SolicitudePDto;
import com.lacv.marketplatform.dtos.process.UsuarioPDto;
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
@RequestMapping(value="/vista/externalService")
public class ExternalServiceViewProcessController extends ExtProcessController {
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ProcessConfig process= new ProcessConfig("externalService", "logProcess", LogProcessDto.class);
        process.setMainProcessTitle("Gestionar Servicios Externos");
        process.addControlProcessView("maquinasNovaventa", "Maquinas Novaventa", SolicitudePDto.class, BasicResultDto.class);
        process.addControlProcessView("maquinasNovaventaXml", "Maquinas Novaventa XML", SolicitudePDto.class, BasicResultDto.class);
        process.addControlProcessView("merakiDevices", "Meraki Devices", NetworkPDto.class, BasicResultDto.class);
        process.addControlProcessView("noticiasCarroya", "Noticias Carroya", BasicPDto.class, BasicResultDto.class);
        process.addControlProcessView("estaInBody", "Esta In Body", UsuarioPDto.class, BasicResultDto.class);
        process.addControlProcessView("estaInParameters", "Esta In Parameters", ProductoPDto.class, BasicResultDto.class);
        super.addControlMapping(process);
        
        MenuItem menuItem= new MenuItem("Procesos", "externalService", "Gestionar Servicios Externos");
        menuItem.setPageType(PageType.PROCESS);
        menuComponent.addItemMenu(menuItem);
        
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
    
}
