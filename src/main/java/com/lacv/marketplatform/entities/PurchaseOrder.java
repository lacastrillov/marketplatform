/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.entities;

import com.dot.gcpbasedot.domain.BaseEntity;
import java.sql.Time;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lacastrillov
 */
@Entity
@Table(name = "purchase_order")
@NamedQueries({
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT p FROM PurchaseOrder p")})
public class PurchaseOrder implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private Long number;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "record_time")
    private Time recordTime;
    @Size(max = 45)
    @Column(name = "total")
    private String total;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseorderDetail> purchaseorderDetailList;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<Payment> paymentList;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id) {
        this.id = id;
    }

    public PurchaseOrder(Integer id, long number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Time getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Time recordTime) {
        this.recordTime = recordTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PurchaseorderDetail> getPurchaseorderDetailList() {
        return purchaseorderDetailList;
    }

    public void setPurchaseorderDetailList(List<PurchaseorderDetail> purchaseorderDetailList) {
        this.purchaseorderDetailList = purchaseorderDetailList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
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
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.PurchaseOrder[ id=" + id + " ]";
    }
    
}
