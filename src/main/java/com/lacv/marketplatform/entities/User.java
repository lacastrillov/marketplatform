/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.entities;

import com.dot.gcpbasedot.domain.BaseEntity;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author grupot
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "link")
    private String link;
    @Column(name = "name")
    private String name;
    @Column(name = "urlPhoto")
    private String urlPhoto;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "tokenUser")
    private String tokenUser;
    @Column(name = "status")
    private String status;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "failedAttempts")
    private Integer failedAttempts;
    @Column(name = "lastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "passwordExpiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordExpiration;
    @OneToMany(mappedBy = "user")
    private List<Commerce> commerceList;
    @OneToMany(mappedBy = "user")
    private List<InventoryOrder> inventoryOrderList;
    @OneToMany(mappedBy = "user")
    private List<PurchaseOrder> purchaseOrderList;
    @OneToMany(mappedBy = "user")
    private List<Supplier> supplierList;
    @OneToMany(mappedBy = "user")
    private List<Payment> paymentList;
    @Transient
    protected Object[] jdoDetachedState;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Date passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.User[ id=" + id + " ]";
    }

    public List<Commerce> getCommerceList() {
        return commerceList;
    }

    public void setCommerceList(List<Commerce> commerceList) {
        this.commerceList = commerceList;
    }

    public List<InventoryOrder> getInventoryOrderList() {
        return inventoryOrderList;
    }

    public void setInventoryOrderList(List<InventoryOrder> inventoryOrderList) {
        this.inventoryOrderList = inventoryOrderList;
    }

    public List<PurchaseOrder> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
    
}
