/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.PurchaseOrderJpa;
import com.lacv.marketplatform.entities.PurchaseOrder;
import com.lacv.marketplatform.mappers.PurchaseOrderMapper;
import com.lacv.marketplatform.services.PurchaseOrderService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends EntityServiceImpl1<PurchaseOrder> implements PurchaseOrderService {
    
    @Autowired
    public PurchaseOrderJpa purchaseOrderJpa;
    
    @Autowired
    public PurchaseOrderMapper purchaseOrderMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return purchaseOrderJpa;
    }
    
}
