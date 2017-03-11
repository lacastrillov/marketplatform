/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.daos.impl;


import com.lacv.marketplatform.daos.SupplierJpa;
import com.lacv.marketplatform.entities.Supplier;
import com.dot.gcpbasedot.dao.JPAAbstractDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lacastrillov
 */
@Repository
public class SupplierJpaController extends JPAAbstractDao<Supplier> implements SupplierJpa {

    @Autowired
    public void init(DataSource dataSource){
        super.setDataSource(dataSource);
    }
    
    @Override
    @PersistenceContext(unitName ="MarketPlatformPU")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager= entityManager;
    }
    
}
