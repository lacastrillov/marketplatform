/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest.config;


import com.dot.gcpbasedot.controller.RestConfigurationController;
import com.lacv.marketplatform.services.config.ContactConfigService;
import com.lacv.marketplatform.services.config.PortalConfigService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/generalConfig")
public class GeneralConfigController extends RestConfigurationController {
    
    @Autowired
    PortalConfigService portalConfigService;
    
    @Autowired
    ContactConfigService contactConfigService;
    
    
    @PostConstruct
    public void init(){
        super.addControlConfigurationObject("portalConfig", portalConfigService);
        super.addControlConfigurationObject("contactConfig", contactConfigService);
    }
    
}
