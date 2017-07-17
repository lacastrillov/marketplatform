/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.dot.gcpbasedot.controller.RestDirectController;
import com.dot.gcpbasedot.service.JdbcDirectService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/direct")
public class DirectController extends RestDirectController {
    
    @Autowired
    JdbcDirectService jdbcDirectService;
    
    
    @PostConstruct
    public void init(){
        super.configRestDirectService(jdbcDirectService, "queriesTableColumn.columsConfig");
    }
    
    
}
