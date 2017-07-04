/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import java.util.Date;

/**
 *
 * @author grupot
 */
public class ActivationProductPDto {
    
    @NotNull
    @Order(1)
    @TextField("Id Producto")
    private Integer productId;
    
    @Order(2)
    @TextField("C&oacute;digo Producto")
    private String productCode;
    
    @Order(3)
    @TextField("Marca")
    private String brand;
    
    @Order(4)
    @TextField("Fecha Registro")
    private Date registerDate;
    
    @Order(5)
    @TextField("Datos de Contacto")
    private ContactUserPDto contactUser;

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the registerDate
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @return the contactUser
     */
    public ContactUserPDto getContactUser() {
        return contactUser;
    }

    /**
     * @param contactUser the contactUser to set
     */
    public void setContactUser(ContactUserPDto contactUser) {
        this.contactUser = contactUser;
    }
    
}
