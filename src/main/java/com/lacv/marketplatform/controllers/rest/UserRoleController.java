/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.UserRoleMapper;
import com.lacv.marketplatform.services.UserRoleService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.service.gcp.StorageService;
import com.lacv.marketplatform.services.security.SecurityService;
import javax.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/userRole")
public class UserRoleController extends RestSessionController {
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    UserRoleMapper userRoleMapper;
    
    @Autowired
    StorageService storageService;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("userRole", userRoleService, userRoleMapper);
    }
    
    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters){
        jsonFilters.getJSONObject("eq").put("user", securityService.getCurrentUser().getId().toString());
                
        return jsonFilters;
    }
}
