/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.entities;

import com.dot.gcpbasedot.domain.BaseEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lacastrillov
 */
@Entity
@Table(name = "inventoryorder_detail")
@NamedQueries({
    @NamedQuery(name = "InventoryorderDetail.findAll", query = "SELECT i FROM InventoryorderDetail i")})
public class InventoryorderDetail implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    private Integer unitPrice;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "inventoryorder_id", referencedColumnName = "id")
    @ManyToOne
    private InventoryOrder inventoryOrder;

    public InventoryorderDetail() {
    }

    public InventoryorderDetail(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InventoryOrder getInventoryOrder() {
        return inventoryOrder;
    }

    public void setInventoryOrder(InventoryOrder inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
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
        if (!(object instanceof InventoryorderDetail)) {
            return false;
        }
        InventoryorderDetail other = (InventoryorderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.InventoryorderDetail[ id=" + id + " ]";
    }
    
}
