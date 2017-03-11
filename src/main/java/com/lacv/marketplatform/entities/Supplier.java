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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lacastrillov
 */
@Entity
@Table(name = "supplier")
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")})
public class Supplier implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 200)
    @Column(name = "contact_name")
    private String contactName;
    @Size(max = 50)
    @Column(name = "contact_title")
    private String contactTitle;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @Size(max = 100)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "region")
    private String region;
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    @Size(max = 50)
    @Column(name = "phone_office")
    private String phoneOffice;
    @Size(max = 50)
    @Column(name = "phone_mobile")
    private String phoneMobile;
    @Size(max = 80)
    @Column(name = "mail")
    private String mail;
    @OneToMany(mappedBy = "supplier")
    private List<Product> productList;
    @OneToMany(mappedBy = "supplier")
    private List<InventoryOrder> inventoryOrderList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    public Supplier() {
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(Integer id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneOffice() {
        return phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<InventoryOrder> getInventoryOrderList() {
        return inventoryOrderList;
    }

    public void setInventoryOrderList(List<InventoryOrder> inventoryOrderList) {
        this.inventoryOrderList = inventoryOrderList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.Supplier[ id=" + id + " ]";
    }
    
}
