/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.lacv.marketplatform.mappers.PaymentMapper;
import com.lacv.marketplatform.services.PaymentService;
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
@RequestMapping(value="/rest/payment")
public class PaymentRestController extends RestSessionController {
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    PaymentMapper paymentMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("payment", paymentService, paymentMapper);
    }

    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters) {
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
    public boolean canCreate(BaseEntity entity) {
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