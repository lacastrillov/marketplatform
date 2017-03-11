/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.UserJpa;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.mappers.UserMapper;
import com.lacv.marketplatform.services.UserService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("userService")
public class UserServiceImpl extends EntityServiceImpl1<User> implements UserService {
    
    @Autowired
    public UserJpa userJpa;
    
    @Autowired
    public UserMapper userMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return userJpa;
    }
    
    
}
