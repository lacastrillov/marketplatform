/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.UserRoleMapper;
import com.lacv.marketplatform.services.UserRoleService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.entities.UserRole;
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
public class UserRoleRestController extends RestSessionController {
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    UserRoleMapper userRoleMapper;
    
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

    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }

    @Override
    public boolean canLoad(BaseEntity entity) {
        UserRole userRole= (UserRole) entity;
        return userRole.getUser().getId().equals(securityService.getCurrentUser().getId());
    }
    
    @Override
    public boolean canCreate(BaseEntity entity){
        return false;
    }

    @Override
    public boolean canUpdate(BaseEntity entity) {
        return false;
    }

    @Override
    public boolean canDelete(BaseEntity entity) {
        return false;
    }

    @Override
    public boolean canUpdateByFilters(JSONObject jsonFilters) {
        return false;
    }

    @Override
    public boolean canDeleteByFilters(JSONObject jsonFilters) {
        return false;
    }

}
