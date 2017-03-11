/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.PropertyJpa;
import com.lacv.marketplatform.entities.Property;
import com.lacv.marketplatform.mappers.PropertyMapper;
import com.lacv.marketplatform.services.PropertyService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("propertyService")
public class PropertyServiceImpl extends EntityServiceImpl1<Property> implements PropertyService {
    
    @Autowired
    public PropertyJpa propertyJpa;
    
    @Autowired
    public PropertyMapper propertyMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return propertyJpa;
    }
    
}
