/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.LogProcessJpa;
import com.lacv.marketplatform.entities.LogProcess;
import com.lacv.marketplatform.mappers.LogProcessMapper;
import com.lacv.marketplatform.services.LogProcessService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("logProcessService")
public class LogProcessServiceImpl extends EntityServiceImpl1<LogProcess> implements LogProcessService {
    
    @Autowired
    public LogProcessJpa logProcessJpa;
    
    @Autowired
    public LogProcessMapper logProcessMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return logProcessJpa;
    }
    
}
