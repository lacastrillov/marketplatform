/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.AuthorizationJpa;
import com.lacv.marketplatform.entities.Authorization;
import com.lacv.marketplatform.mappers.AuthorizationMapper;
import com.lacv.marketplatform.services.AuthorizationService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("authorizationService")
public class AuthorizationServiceImpl extends EntityServiceImpl1<Authorization> implements AuthorizationService {
    
    @Autowired
    public AuthorizationJpa authorizationJpa;
    
    @Autowired
    public AuthorizationMapper authorizationMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return authorizationJpa;
    }
    
}
