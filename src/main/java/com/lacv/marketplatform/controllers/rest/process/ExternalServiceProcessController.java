/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest.process;


import com.lacv.marketplatform.entities.LogProcess;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.services.LogProcessService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.dto.ExternalServiceDto;
import com.lacv.marketplatform.dtos.process.NetworkPDto;
import com.lacv.marketplatform.dtos.process.ProductoPDto;
import com.lacv.marketplatform.dtos.process.SolicitudePDto;
import com.lacv.marketplatform.dtos.process.UsuarioPDto;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/externalService")
public class ExternalServiceProcessController extends RestController {
    
    @Autowired
    LogProcessService logProcessService;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        super.addControlProcess("externalService", LogProcess.class, logProcessService);
        
        ExternalServiceDto service1= new ExternalServiceDto("maquinasNovaventa", "https://portal-contenido-novaventa.appspot.com/rest/{entity}/find.htm", HttpMethod.GET, SolicitudePDto.class);
        super.enableExternalService(service1);
        
        ExternalServiceDto service2= new ExternalServiceDto("merakiDevices", "https://dashboard.meraki.com/api/v0/networks/{networkId}/devices", HttpMethod.GET, NetworkPDto.class);
        super.enableExternalService(service2);
        
        ExternalServiceDto service3= new ExternalServiceDto("maquinasNovaventa", "https://portal-contenido-novaventa.appspot.com/rest/{entity}/find.htm", HttpMethod.GET, SolicitudePDto.class);
        super.enableExternalService(service3);
        
        ExternalServiceDto service4= new ExternalServiceDto("estaInBody", "http://localhost:8084/tempprocess/inbody", HttpMethod.POST, UsuarioPDto.class);
        super.enableExternalService(service4);
        
        ExternalServiceDto service5= new ExternalServiceDto("estaInParameters", "http://localhost:8084/tempprocess/inparameters", HttpMethod.POST, ProductoPDto.class);
        super.enableExternalService(service5);
    }
    
    @Override
    public String getClientId(){
        User user= securityService.getCurrentUser();
        return user.getUsername();
    }
    
    
}
