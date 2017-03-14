/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.PaymentDto;
import com.lacv.marketplatform.mappers.PaymentMapper;
import com.lacv.marketplatform.services.PaymentService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/payment")
public class PaymentViewController extends ExtViewController {
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    PaymentMapper paymentMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("payment", "referenceNumber", paymentService, PaymentDto.class);
        view.setSingularEntityTitle("Pago");
        view.setPluralEntityTitle("Pagos");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Pagos", "payment", "Gestionar Pagos");
        menuItem.setParentPosition(10);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
