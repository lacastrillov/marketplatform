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
import java.util.List;

/**
 *
 * @author lacastrillov
 */
public class SupplierDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @TextField("Empresa")
    private String companyName;
    
    @TextField("Nombre Contacto")
    private String contactName;
    
    @TextField("Cargo Contacto")
    private String contactTitle;
    
    @TextField("Direcci&oacute;n")
    private String address;
    
    @TextField("Ciudad")
    private String city;
    
    @TextField("Departamento")
    private String region;
    
    @TextField("Pais")
    private String country;
    
    @TextField("Tel&eacute;fono Fijo")
    private String phoneOffice;
    
    @TextField("Celular")
    private String phoneMobile;
    
    @TextField("Correo")
    @TypeFormField(FieldType.EMAIL)
    private String mail;
    
    @TextField("Usuario")
    private UserDto user;
    
    private List<ProductDto> productList;
    
    private List<InventoryOrderDto> inventoryOrderList;
    

    public SupplierDto() {
    }

    public SupplierDto(Integer id) {
        this.id = id;
    }

    public SupplierDto(Integer id, String companyName) {
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

    public List<ProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDto> productList) {
        this.productList = productList;
    }

    public List<InventoryOrderDto> getInventoryOrderList() {
        return inventoryOrderList;
    }

    public void setInventoryOrderList(List<InventoryOrderDto> inventoryOrderList) {
        this.inventoryOrderList = inventoryOrderList;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
        if (!(object instanceof SupplierDto)) {
            return false;
        }
        SupplierDto other = (SupplierDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.SupplierDto[ id=" + id + " ]";
    }
    
}
