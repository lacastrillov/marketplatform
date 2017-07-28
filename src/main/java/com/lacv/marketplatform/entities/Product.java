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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")})
public class Product implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 100)
    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;
    @Column(name = "seggested_unit_price")
    private Integer seggestedUnitPrice;
    @Column(name = "buy_unit_price")
    private Integer buyUnitPrice;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "units_in_stock")
    private Integer unitsInStock;
    @Column(name = "units_in_order")
    private Integer unitsInOrder;
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Size(max = 200)
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "order_level")
    private Integer orderLevel;
    @Column(name = "featured")
    private Boolean featured;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Category category;
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    @ManyToOne
    private SubCategory subCategory;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplier;
    @JoinColumn(name = "commerce_id", referencedColumnName = "commerce_id")
    @ManyToOne
    private Commerce commerce;
    @OneToMany(mappedBy = "product")
    private List<InventoryorderDetail> inventoryorderDetailList;
    @OneToMany(mappedBy = "product")
    private List<PurchaseorderDetail> purchaseorderDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> productImageList;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String code, String name) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    public List<InventoryorderDetail> getInventoryorderDetailList() {
        return inventoryorderDetailList;
    }

    public void setInventoryorderDetailList(List<InventoryorderDetail> inventoryorderDetailList) {
        this.inventoryorderDetailList = inventoryorderDetailList;
    }

    public List<PurchaseorderDetail> getPurchaseorderDetailList() {
        return purchaseorderDetailList;
    }

    public void setPurchaseorderDetailList(List<PurchaseorderDetail> purchaseorderDetailList) {
        this.purchaseorderDetailList = purchaseorderDetailList;
    }
    
    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.Product[ id=" + id + " ]";
    }

    }
