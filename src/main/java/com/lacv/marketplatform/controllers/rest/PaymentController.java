/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.PaymentMapper;
import com.lacv.marketplatform.services.PaymentService;
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
@RequestMapping(value="/rest/payment")
public class PaymentController extends RestController {
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    PaymentMapper paymentMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("payment", paymentService, paymentMapper);
    }
    
    
}
