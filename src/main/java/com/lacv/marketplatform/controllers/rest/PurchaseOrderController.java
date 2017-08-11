/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.PurchaseOrderMapper;
import com.lacv.marketplatform.services.PurchaseOrderService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.entities.PurchaseOrder;
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
@RequestMapping(value="/rest/purchaseOrder")
public class PurchaseOrderController extends RestSessionController {
    
    @Autowired
    PurchaseOrderService purchaseOrderService;
    
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("purchaseOrder", purchaseOrderService, purchaseOrderMapper);
    }
    
    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters){
        jsonFilters.getJSONObject("eq").put("user", securityService.getCurrentUser().getId());
                
        return jsonFilters;
    }
    
    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }
    
    @Override
    public boolean canLoad(BaseEntity entity){
        PurchaseOrder purchaseOrder= (PurchaseOrder) entity;
        return securityService.getCurrentUser().getId().equals(purchaseOrder.getUser().getId());
    }
    
    @Override
    public boolean canCreate(BaseEntity entity){
        return false;
    }
    
    @Override
    public boolean canUpdate(BaseEntity entity){
        return false;
    }
    
    @Override
    public boolean canDelete(BaseEntity entity){
        return false;
    }
    
}
