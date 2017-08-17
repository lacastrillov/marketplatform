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
    @TextField("Código")
    private String code;
    
    @Order(3)
    @TextField("Nombre")
    private String name;
    
    @Order(5)
    @TextField("Marca")
    private String brand;
    
    @Order(6)
    @TextField("Cantidad por unidad")
    private String quantity_per_unit;
    
    @Order(7)
    @TextField("Precio Segerido")
    private Integer seggested_unit_price;
    
    @Order(8)
    @TextField("Precio Unitario")
    private Integer buy_unit_price;
    
    @Order(9)
    @TextField("Descuento")
    private Integer discount;
    
    @Order(10)
    @TextField("Unidades en Stock")
    private Integer units_in_stock;
    
    @Order(11)
    @TextField("Unidades en Ordenes")
    private Integer units_in_order;
    
    @Order(12)
    @TextField("Fecha Registro")
    private Date register_date;
    
    @Order(13)
    @TextField("Palabras clave")
    private String keywords;
    
    @Order(14)
    @TextField("Orden")
    private Integer order_level;
    
    @Order(15)
    @TextField("Descripción")
    @TypeFormField(FieldType.TEXT_AREA)
    private String description;
    
    @Order(16)
    @TextField("Destacado")
    private Boolean featured;
    
    @Order(17)
    @TextField("Estado")
    private String status;
    
    @Order(18)
    @TextField("Categoría")
    private String category;
    
    @Order(19)
    @TextField("Sub Categoría")
    private String subcategory;
    
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
     * @return the quantity_per_unit
     */
    public String getQuantity_per_unit() {
        return quantity_per_unit;
    }

    /**
     * @param quantity_per_unit the quantity_per_unit to set
     */
    public void setQuantity_per_unit(String quantity_per_unit) {
        this.quantity_per_unit = quantity_per_unit;
    }

    /**
     * @return the seggested_unit_price
     */
    public Integer getSeggested_unit_price() {
        return seggested_unit_price;
    }

    /**
     * @param seggested_unit_price the seggested_unit_price to set
     */
    public void setSeggested_unit_price(Integer seggested_unit_price) {
        this.seggested_unit_price = seggested_unit_price;
    }

    /**
     * @return the buy_unit_price
     */
    public Integer getBuy_unit_price() {
        return buy_unit_price;
    }

    /**
     * @param buy_unit_price the buy_unit_price to set
     */
    public void setBuy_unit_price(Integer buy_unit_price) {
        this.buy_unit_price = buy_unit_price;
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
     * @return the units_in_stock
     */
    public Integer getUnits_in_stock() {
        return units_in_stock;
    }

    /**
     * @param units_in_stock the units_in_stock to set
     */
    public void setUnits_in_stock(Integer units_in_stock) {
        this.units_in_stock = units_in_stock;
    }

    /**
     * @return the units_in_order
     */
    public Integer getUnits_in_order() {
        return units_in_order;
    }

    /**
     * @param units_in_order the units_in_order to set
     */
    public void setUnits_in_order(Integer units_in_order) {
        this.units_in_order = units_in_order;
    }

    /**
     * @return the register_date
     */
    public Date getRegister_date() {
        return register_date;
    }

    /**
     * @param register_date the register_date to set
     */
    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
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
     * @return the order_level
     */
    public Integer getOrder_level() {
        return order_level;
    }

    /**
     * @param order_level the order_level to set
     */
    public void setOrder_level(Integer order_level) {
        this.order_level = order_level;
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
     * @return the subcategory
     */
    public String getSubcategory() {
        return subcategory;
    }

    /**
     * @param subcategory the subcategory to set
     */
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
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
