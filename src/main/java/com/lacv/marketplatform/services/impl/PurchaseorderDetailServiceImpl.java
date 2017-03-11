/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.PurchaseorderDetailJpa;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
import com.lacv.marketplatform.mappers.PurchaseorderDetailMapper;
import com.lacv.marketplatform.services.PurchaseorderDetailService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("purchaseorderDetailService")
public class PurchaseorderDetailServiceImpl extends EntityServiceImpl1<PurchaseorderDetail> implements PurchaseorderDetailService {
    
    @Autowired
    public PurchaseorderDetailJpa purchaseorderDetailJpa;
    
    @Autowired
    public PurchaseorderDetailMapper purchaseorderDetailMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return purchaseorderDetailJpa;
    }
    
}
