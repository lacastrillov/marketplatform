/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.WebresourceAuthorizationJpa;
import com.lacv.marketplatform.entities.WebresourceAuthorization;
import com.lacv.marketplatform.mappers.WebresourceAuthorizationMapper;
import com.lacv.marketplatform.services.WebresourceAuthorizationService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("webresourceAuthorizationService")
public class WebresourceAuthorizationServiceImpl extends EntityServiceImpl1<WebresourceAuthorization> implements WebresourceAuthorizationService {
    
    @Autowired
    public WebresourceAuthorizationJpa webresourceAuthorizationJpa;
    
    @Autowired
    public WebresourceAuthorizationMapper webresourceAuthorizationMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return webresourceAuthorizationJpa;
    }
    
}
