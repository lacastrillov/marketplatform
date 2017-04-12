/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.WebResourceJpa;
import com.lacv.marketplatform.entities.WebResource;
import com.lacv.marketplatform.mappers.WebResourceMapper;
import com.lacv.marketplatform.services.WebResourceService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("webResourceService")
public class WebResourceServiceImpl extends EntityServiceImpl1<WebResource> implements WebResourceService {
    
    @Autowired
    public WebResourceJpa webResourceJpa;
    
    @Autowired
    public WebResourceMapper webResourceMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return webResourceJpa;
    }
    
}
