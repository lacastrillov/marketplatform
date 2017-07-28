/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services;

import com.lacv.marketplatform.entities.Property;
import com.dot.gcpbasedot.service.EntityService;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;



/**
 *
 * @author lacastrillov
 */
public interface PropertyService extends EntityService<Property> {
    
    String getString(String propertyName);
    
    Character getCharacter(String propertyName);
    
    Integer getInteger(String propertyName);
    
    Long getLong(String propertyName);
    
    Double getDouble(String propertyName);
    
    Float getFloat(String propertyName);
    
    BigInteger getBigInteger(String propertyName);
    
    Boolean getBoolean(String propertyName);
    
    Date getDate(String propertyName);
    
    Time getTime(String propertyName);
    
}
