/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.TableColumnJpa;
import com.lacv.marketplatform.entities.TableColumn;
import com.lacv.marketplatform.mappers.TableColumnMapper;
import com.lacv.marketplatform.services.TableColumnService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nalvarez
 */
@Service("tableColumnService")
public class TableColumnServiceImpl extends EntityServiceImpl1<TableColumn> implements TableColumnService {
    
    @Autowired
    public TableColumnJpa tableColumnJpa;
    
    @Autowired
    public TableColumnMapper tableColumnMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return tableColumnJpa;
    }
    
}
