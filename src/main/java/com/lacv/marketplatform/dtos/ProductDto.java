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
import java.util.Date;
import java.util.List;

/**
 *
 * @author lacastrillov
 */
public class ProductDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
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
    @TypeFormField(value = FieldType.LIST, data = {"Publicado", "Agotado", "Despublicado", "Descontinuado", "Eliminado"})
    private String status;
    
    @Order(18)
    @TextField("Categor&iacute;a")
    private CategoryDto category;
    
    @Order(19)
    @TextField("Sub Categor&iacute;a")
    private SubCategoryDto subCategory;
    
    @Order(20)
    @TextField("Proveedor")
    private SupplierDto supplier;
    
    @Order(21)
    @TextField("Comercio")
    private CommerceDto commerce;
    
    private List<InventoryorderDetailDto> inventoryorderDetailList;
    
    private List<PurchaseorderDetailDto> purchaseorderDetailList;

    public ProductDto() {
    }

    public ProductDto(Integer id) {
        this.id = id;
    }

    public ProductDto(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Integer getSeggestedUnitPrice() {
        return seggestedUnitPrice;
    }

    public void setSeggestedUnitPrice(Integer seggestedUnitPrice) {
        this.seggestedUnitPrice = seggestedUnitPrice;
    }

    public Integer getBuyUnitPrice() {
        return buyUnitPrice;
    }

    public void setBuyUnitPrice(Integer buyUnitPrice) {
        this.buyUnitPrice = buyUnitPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Integer getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(Integer unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public SubCategoryDto getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDto subCategory) {
        this.subCategory = subCategory;
    }

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }
    
    public CommerceDto getCommerce() {
        return commerce;
    }

    public void setCommerce(CommerceDto commerce) {
        this.commerce = commerce;
    }

    public List<InventoryorderDetailDto> getInventoryorderDetailList() {
        return inventoryorderDetailList;
    }

    public void setInventoryorderDetailList(List<InventoryorderDetailDto> inventoryorderDetailList) {
        this.inventoryorderDetailList = inventoryorderDetailList;
    }

    public List<PurchaseorderDetailDto> getPurchaseorderDetailList() {
        return purchaseorderDetailList;
    }

    public void setPurchaseorderDetailList(List<PurchaseorderDetailDto> purchaseorderDetailList) {
        this.purchaseorderDetailList = purchaseorderDetailList;
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
        if (!(object instanceof ProductDto)) {
            return false;
        }
        ProductDto other = (ProductDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.ProductDto[ id=" + id + " ]";
    }
    
}
