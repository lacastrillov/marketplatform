/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.RoleAuthorizationMapper;
import com.lacv.marketplatform.services.RoleAuthorizationService;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.service.gcp.StorageService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/roleAuthorization")
public class RoleAuthorizationController extends RestController {
    
    @Autowired
    RoleAuthorizationService roleAuthorizationService;
    
    @Autowired
    RoleAuthorizationMapper roleAuthorizationMapper;
    
    @Autowired
    StorageService storageService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("roleAuthorization", roleAuthorizationService, roleAuthorizationMapper);
    }
    
    
}