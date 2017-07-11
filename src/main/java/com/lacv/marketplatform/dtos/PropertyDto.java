/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.ReadOnly;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author lacastrillov
 */
public class PropertyDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @TextField("Llave")
    private String key;
    
    @TextField("Valor")
    private String value;
    
    @TextField("Tipo")
    @TypeFormField(value=FieldType.LIST, data={"String","Number","Date","Time","Boolean","Character"})
    private String type;
    
    @TextField("Estado")
    @TypeFormField(value = FieldType.LIST, data = {"Activa", "Inactiva", "Bloqueada", "Eliminada"})
    private String status;

    public PropertyDto() {
    }

    public PropertyDto(Integer id) {
        this.id = id;
    }

    public PropertyDto(Integer id, String key) {
        this.id = id;
        this.key = key;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyDto)) {
            return false;
        }
        PropertyDto other = (PropertyDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.PropertyDto[ id=" + id + " ]";
    }
    
}
