/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.InventoryorderDetailDto;
import com.lacv.marketplatform.mappers.InventoryorderDetailMapper;
import com.lacv.marketplatform.services.InventoryorderDetailService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.config.EntityConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/inventoryorderDetail")
public class InventoryorderDetailViewController extends ExtEntityController {
    
    @Autowired
    InventoryorderDetailService inventoryorderDetailService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    InventoryorderDetailMapper inventoryorderDetailMapper;
    
    
    @PostConstruct
    public void init(){
        EntityConfig view= new EntityConfig("inventoryorderDetail", "id", inventoryorderDetailService, InventoryorderDetailDto.class);
        view.setSingularEntityTitle("Detalle Orden de Inventario");
        view.setPluralEntityTitle("Detalle Orden de Inventario");
        super.addControlMapping(view);
    }
    
    
}
