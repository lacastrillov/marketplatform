/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.WebresourceRoleJpa;
import com.lacv.marketplatform.entities.WebresourceRole;
import com.lacv.marketplatform.mappers.WebresourceRoleMapper;
import com.lacv.marketplatform.services.WebresourceRoleService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("webresourceRoleService")
public class WebresourceRoleServiceImpl extends EntityServiceImpl1<WebresourceRole> implements WebresourceRoleService {
    
    @Autowired
    public WebresourceRoleJpa webresourceRoleJpa;
    
    @Autowired
    public WebresourceRoleMapper webresourceRoleMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return webresourceRoleJpa;
    }
    
}
