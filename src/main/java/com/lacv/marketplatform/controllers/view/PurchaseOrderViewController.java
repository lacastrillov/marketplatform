/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.PurchaseOrderDto;
import com.lacv.marketplatform.mappers.PurchaseOrderMapper;
import com.lacv.marketplatform.services.PurchaseOrderService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/purchaseOrder")
public class PurchaseOrderViewController extends ExtViewController {
    
    @Autowired
    PurchaseOrderService purchaseOrderService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("purchaseOrder", "number", purchaseOrderService, PurchaseOrderDto.class);
        view.setSingularEntityTitle("Orden de Compra");
        view.setPluralEntityTitle("Ordenes de Compra");
        view.addChildExtView("purchaseorderDetail", PurchaseorderDetail.class, ViewConfig.TCV_STANDARD);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Ordenes de Compra", "purchaseOrder", "Gestionar Ordenes de Compra");
        menuItem.setParentPosition(9);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    
}
