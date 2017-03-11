/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.RoleJpa;
import com.lacv.marketplatform.entities.Role;
import com.lacv.marketplatform.mappers.RoleMapper;
import com.lacv.marketplatform.services.RoleService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("roleService")
public class RoleServiceImpl extends EntityServiceImpl1<Role> implements RoleService {
    
    @Autowired
    public RoleJpa roleJpa;
    
    @Autowired
    public RoleMapper roleMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return roleJpa;
    }
    
}
