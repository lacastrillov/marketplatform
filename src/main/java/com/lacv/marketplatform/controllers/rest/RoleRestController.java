/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.RoleMapper;
import com.lacv.marketplatform.services.RoleService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/role")
public class RoleRestController extends RestSessionController {
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    RoleMapper roleMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("role", roleService, roleMapper);
    }
    
    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters){
        JSONArray roleIds= new JSONArray();
        List<UserRole> userRoles= userRoleService.findByParameter("user", securityService.getCurrentUser());
        for(UserRole userRole: userRoles){
            roleIds.put(userRole.getRole().getId());
        }
        jsonFilters.getJSONObject("in").put("id", roleIds);
                
        return jsonFilters;
    }

    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }

    @Override
    public boolean canLoad(BaseEntity entity) {
        return true;
    }

    @Override
    public boolean canUpdate(BaseEntity entity) {
        return false;
    }
    
    @Override
    public boolean canCreate(BaseEntity entity){
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
