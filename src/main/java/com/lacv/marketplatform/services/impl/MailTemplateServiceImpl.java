/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.MailTemplateJpa;
import com.lacv.marketplatform.entities.MailTemplate;
import com.lacv.marketplatform.mappers.MailTemplateMapper;
import com.lacv.marketplatform.services.MailTemplateService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("mailTemplateService")
public class MailTemplateServiceImpl extends EntityServiceImpl1<MailTemplate> implements MailTemplateService {
    
    @Autowired
    public MailTemplateJpa mailTemplateJpa;
    
    @Autowired
    public MailTemplateMapper mailTemplateMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return mailTemplateJpa;
    }
    
}
