/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services;

import com.lacv.marketplatform.entities.PurchaseOrder;
import com.dot.gcpbasedot.service.EntityService;
import com.lacv.marketplatform.dtos.process.ShoppingCartPDto;
import com.lacv.marketplatform.entities.User;



/**
 *
 * @author lacastrillov
 */
public interface PurchaseOrderService extends EntityService<PurchaseOrder> {
    
    Long generatePurchaseOrder(ShoppingCartPDto shoppingCartPDto, User buyerUser);
    
}
