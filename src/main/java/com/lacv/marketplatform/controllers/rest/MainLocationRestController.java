/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.mappers.MainLocationMapper;
import com.lacv.marketplatform.services.MainLocationService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/mainLocation")
public class MainLocationRestController extends RestController {
    
    @Autowired
    MainLocationService mainLocationService;
    
    @Autowired
    MainLocationMapper mainLocationMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("mainLocation", mainLocationService, mainLocationMapper);
    }
    
}
