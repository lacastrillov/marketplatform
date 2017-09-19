/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.InventoryorderDetailMapper;
import com.lacv.marketplatform.services.InventoryorderDetailService;
import com.dot.gcpbasedot.controller.RestEntityController;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/inventoryorderDetail")
public class InventoryorderDetailRestController extends RestEntityController {
    
    @Autowired
    InventoryorderDetailService inventoryorderDetailService;
    
    @Autowired
    InventoryorderDetailMapper inventoryorderDetailMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("inventoryorderDetail", inventoryorderDetailService, inventoryorderDetailMapper);
    }
    
    
}
