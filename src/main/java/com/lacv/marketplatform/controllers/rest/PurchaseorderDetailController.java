/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.PurchaseorderDetailMapper;
import com.lacv.marketplatform.services.PurchaseorderDetailService;
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
@RequestMapping(value="/rest/purchaseorderDetail")
public class PurchaseorderDetailController extends RestController {
    
    @Autowired
    PurchaseorderDetailService purchaseorderDetailService;
    
    @Autowired
    PurchaseorderDetailMapper purchaseorderDetailMapper;
    
    @Autowired
    StorageService storageService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("purchaseorderDetail", purchaseorderDetailService, purchaseorderDetailMapper);
    }
    
    
}
