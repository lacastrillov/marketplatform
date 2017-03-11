/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.UserRoleJpa;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.mappers.UserRoleMapper;
import com.lacv.marketplatform.services.UserRoleService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends EntityServiceImpl1<UserRole> implements UserRoleService {
    
    @Autowired
    public UserRoleJpa userRoleJpa;
    
    @Autowired
    public UserRoleMapper userRoleMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return userRoleJpa;
    }
    
}
