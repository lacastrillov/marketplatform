/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.SupplierJpa;
import com.lacv.marketplatform.entities.Supplier;
import com.lacv.marketplatform.mappers.SupplierMapper;
import com.lacv.marketplatform.services.SupplierService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("supplierService")
public class SupplierServiceImpl extends EntityServiceImpl1<Supplier> implements SupplierService {
    
    @Autowired
    public SupplierJpa supplierJpa;
    
    @Autowired
    public SupplierMapper supplierMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return supplierJpa;
    }
    
}
