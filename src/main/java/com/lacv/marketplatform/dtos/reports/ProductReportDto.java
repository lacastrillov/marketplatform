/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.reports;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;
import java.util.Date;

/**
 *
 * @author grupot
 */
public class ProductReportDto {
    
    @Order(1)
    @ColumnWidth(100)
    private Integer id;
    
    @Order(2)
    @TextField("C&oacute;odigo")
    private String code;
    
    @Order(3)
    @TextField("Nombre")
    private String name;
    
    @Order(5)
    @TextField("Marca")
    private String brand;
    
    @Order(6)
    @TextField("Cantidad por unidad")
    private String quantityPerUnit;
    
    @Order(7)
    @TextField("Precio Segerido")
    private Integer seggestedUnitPrice;
    
    @Order(8)
    @TextField("Precio Unitario")
    private Integer buyUnitPrice;
    
    @Order(9)
    @TextField("Descuento")
    private Integer discount;
    
    @Order(10)
    @TextField("Unidades en Stock")
    private Integer unitsInStock;
    
    @Order(11)
    @TextField("Unidades en Ordenes")
    private Integer unitsInOrder;
    
    @Order(12)
    @TextField("Fecha Registro")
    private Date registerDate;
    
    @Order(13)
    @TextField("Palabras clave")
    private String keywords;
    
    @Order(14)
    @TextField("Orden")
    private Integer orderLevel;
    
    @Order(15)
    @TextField("Descripci&oacute;n")
    @TypeFormField(FieldType.TEXT_AREA)
    private String description;
    
    @Order(16)
    @TextField("Destacado")
    private Boolean featured;
    
    @Order(17)
    @TextField("Estado")
    private String status;
    
    @Order(18)
    @TextField("Categor&iacute;a")
    private String category;
    
    @Order(19)
    @TextField("Sub Categor&iacute;a")
    private String subCategory;
    
    @Order(20)
    @TextField("Proveedor")
    private String supplier;
    
    @Order(21)
    @TextField("Comercio")
    private String commerce;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the quantityPerUnit
     */
    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    /**
     * @param quantityPerUnit the quantityPerUnit to set
     */
    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    /**
     * @return the seggestedUnitPrice
     */
    public Integer getSeggestedUnitPrice() {
        return seggestedUnitPrice;
    }

    /**
     * @param seggestedUnitPrice the seggestedUnitPrice to set
     */
    public void setSeggestedUnitPrice(Integer seggestedUnitPrice) {
        this.seggestedUnitPrice = seggestedUnitPrice;
    }

    /**
     * @return the buyUnitPrice
     */
    public Integer getBuyUnitPrice() {
        return buyUnitPrice;
    }

    /**
     * @param buyUnitPrice the buyUnitPrice to set
     */
    public void setBuyUnitPrice(Integer buyUnitPrice) {
        this.buyUnitPrice = buyUnitPrice;
    }

    /**
     * @return the discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * @return the unitsInStock
     */
    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    /**
     * @param unitsInStock the unitsInStock to set
     */
    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    /**
     * @return the unitsInOrder
     */
    public Integer getUnitsInOrder() {
        return unitsInOrder;
    }

    /**
     * @param unitsInOrder the unitsInOrder to set
     */
    public void setUnitsInOrder(Integer unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
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
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the orderLevel
     */
    public Integer getOrderLevel() {
        return orderLevel;
    }

    /**
     * @param orderLevel the orderLevel to set
     */
    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the featured
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     * @param featured the featured to set
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the subCategory
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * @param subCategory the subCategory to set
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the commerce
     */
    public String getCommerce() {
        return commerce;
    }

    /**
     * @param commerce the commerce to set
     */
    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }
    
    
}
