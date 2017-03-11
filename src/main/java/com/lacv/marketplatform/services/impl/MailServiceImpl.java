/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.MailJpa;
import com.lacv.marketplatform.entities.Mail;
import com.lacv.marketplatform.mappers.MailMapper;
import com.lacv.marketplatform.services.MailService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("mailService")
public class MailServiceImpl extends EntityServiceImpl1<Mail> implements MailService {
    
    @Autowired
    public MailJpa mailJpa;
    
    @Autowired
    public MailMapper mailMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return mailJpa;
    }
    
}
