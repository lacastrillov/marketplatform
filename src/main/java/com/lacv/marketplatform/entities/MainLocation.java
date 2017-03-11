/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.entities;

import com.dot.gcpbasedot.domain.BaseEntity;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author grupot
 */
@Entity
@Table(name = "main_location")
@NamedQueries({
    @NamedQuery(name = "MainLocation.findAll", query = "SELECT m FROM MainLocation m")})
public class MainLocation implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMainLocation")
    private Integer id;
    @Size(max = 500)
    @Column(name = "ml_description")
    private String mlDescription;
    @Size(max = 200)
    @Column(name = "ml_location")
    private String mlLocation;
    @Size(max = 100)
    @Column(name = "ml_name")
    private String mlName;
    @OneToMany(mappedBy = "mainLocation")
    private List<Commerce> commerceList;
    @Transient
    protected Object[] jdoDetachedState;

    public MainLocation() {
    }

    public MainLocation(Integer idMainLocation) {
        this.id = idMainLocation;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getMlDescription() {
        return mlDescription;
    }

    public void setMlDescription(String mlDescription) {
        this.mlDescription = mlDescription;
    }

    public String getMlLocation() {
        return mlLocation;
    }

    public void setMlLocation(String mlLocation) {
        this.mlLocation = mlLocation;
    }

    public String getMlName() {
        return mlName;
    }

    public void setMlName(String mlName) {
        this.mlName = mlName;
    }

    public List<Commerce> getCommerceList() {
        return commerceList;
    }

    public void setCommerceList(List<Commerce> commerceList) {
        this.commerceList = commerceList;
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
        if (!(object instanceof MainLocation)) {
            return false;
        }
        MainLocation other = (MainLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.MainLocation[ idMainLocation=" + id + " ]";
    }
    
}
