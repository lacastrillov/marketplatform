/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.ProductJpa;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.mappers.ProductMapper;
import com.lacv.marketplatform.services.ProductService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("productService")
public class ProductServiceImpl extends EntityServiceImpl1<Product> implements ProductService {
    
    @Autowired
    public ProductJpa productJpa;
    
    @Autowired
    public ProductMapper productMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return productJpa;
    }
    
}
