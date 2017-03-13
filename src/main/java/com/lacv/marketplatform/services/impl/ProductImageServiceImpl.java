/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.ProductImageJpa;
import com.lacv.marketplatform.entities.ProductImage;
import com.lacv.marketplatform.mappers.ProductImageMapper;
import com.lacv.marketplatform.services.ProductImageService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("productImageService")
public class ProductImageServiceImpl extends EntityServiceImpl1<ProductImage> implements ProductImageService {
    
    @Autowired
    public ProductImageJpa productImageJpa;
    
    @Autowired
    public ProductImageMapper productImageMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return productImageJpa;
    }
    
}
