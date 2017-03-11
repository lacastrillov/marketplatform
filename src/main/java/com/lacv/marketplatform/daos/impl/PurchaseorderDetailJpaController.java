/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.daos.impl;


import com.lacv.marketplatform.daos.PurchaseorderDetailJpa;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
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
public class PurchaseorderDetailJpaController extends JPAAbstractDao<PurchaseorderDetail> implements PurchaseorderDetailJpa {

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
