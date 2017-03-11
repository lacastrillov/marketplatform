/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest.process;


import com.lacv.marketplatform.mappers.LogProcessMapper;
import com.lacv.marketplatform.services.LogProcessService;
import com.dot.gcpbasedot.controller.RestController;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/logProcess")
public class LogProcessController extends RestController {
    
    @Autowired
    LogProcessService logProcessService;
    
    @Autowired
    LogProcessMapper logProcessMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("logProcess", logProcessService, logProcessMapper);
    }
    
}
