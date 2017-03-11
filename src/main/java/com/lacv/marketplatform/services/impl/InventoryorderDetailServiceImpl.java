/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.InventoryorderDetailJpa;
import com.lacv.marketplatform.entities.InventoryorderDetail;
import com.lacv.marketplatform.mappers.InventoryorderDetailMapper;
import com.lacv.marketplatform.services.InventoryorderDetailService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("inventoryorderDetailService")
public class InventoryorderDetailServiceImpl extends EntityServiceImpl1<InventoryorderDetail> implements InventoryorderDetailService {
    
    @Autowired
    public InventoryorderDetailJpa inventoryorderDetailJpa;
    
    @Autowired
    public InventoryorderDetailMapper inventoryorderDetailMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return inventoryorderDetailJpa;
    }
    
}
