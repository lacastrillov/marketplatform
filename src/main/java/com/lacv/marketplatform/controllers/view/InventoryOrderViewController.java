/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.InventoryOrderDto;
import com.lacv.marketplatform.mappers.InventoryOrderMapper;
import com.lacv.marketplatform.services.InventoryOrderService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.lacv.marketplatform.entities.InventoryorderDetail;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/inventoryOrder")
public class InventoryOrderViewController extends ExtViewController {
    
    @Autowired
    InventoryOrderService inventoryOrderService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    InventoryOrderMapper inventoryOrderMapper;
    
    @Autowired
    SecurityService securityService;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("inventoryOrder", "number", inventoryOrderService, InventoryOrderDto.class);
        view.setSingularEntityTitle("Orden de Inventario");
        view.setPluralEntityTitle("Ordenes de Inventario");
        view.addChildExtView("inventoryorderDetail", InventoryorderDetail.class, ViewConfig.TCV_STANDARD);
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Pedidos", "inventoryOrder", "Gestionar Ordenes de Inventario");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }
    
    
}
