/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.PurchaseorderDetailMapper;
import com.lacv.marketplatform.services.PurchaseorderDetailService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.entities.PurchaseOrder;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
import com.lacv.marketplatform.services.PurchaseOrderService;
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
@RequestMapping(value="/rest/purchaseorderDetail")
public class PurchaseorderDetailRestController extends RestSessionController {
    
    @Autowired
    PurchaseorderDetailService purchaseorderDetailService;
    
    @Autowired
    PurchaseOrderService purchaseOrderService;
    
    @Autowired
    PurchaseorderDetailMapper purchaseorderDetailMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("purchaseorderDetail", purchaseorderDetailService, purchaseorderDetailMapper);
    }
    
    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters){
        List<PurchaseOrder> purchaseOrders= purchaseOrderService.findByParameter("user", securityService.getCurrentUser());
        JSONArray purchaseOrderIds= new JSONArray();
        for(PurchaseOrder purchaseOrder: purchaseOrders){
            purchaseOrderIds.put(purchaseOrder.getId());
        }
        jsonFilters.getJSONObject("in").put("purchaseOrder", purchaseOrderIds);
                
        return jsonFilters;
    }

    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }

    @Override
    public boolean canLoad(BaseEntity entity) {
        PurchaseorderDetail purchaseorderDetail= (PurchaseorderDetail) entity;
        return securityService.getCurrentUser().getId().equals(purchaseorderDetail.getPurchaseOrder().getUser().getId());
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
    
    
}
