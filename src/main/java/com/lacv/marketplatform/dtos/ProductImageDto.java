/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.HideField;
import com.dot.gcpbasedot.annotation.ImageResize;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.ReadOnly;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;
import com.dot.gcpbasedot.enums.HideView;

/**
 *
 * @author grupot
 */
public class ProductImageDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @Order(2)
    @TextField("Imagen")
    @TypeFormField(FieldType.IMAGE_FILE_UPLOAD)
    @ImageResize({"200,200", "500,500", "800,800"})
    @HideField({HideView.FILTER})
    @ColumnWidth(300)
    private String image;
    
    @Order(3)
    @TextField("Orden")
    private Integer order;
    
    @Order(4)
    @TextField("Producto")
    private ProductDto product;

    public ProductImageDto() {
    }

    public ProductImageDto(Integer id) {
        this.id = id;
    }

    public ProductImageDto(Integer id, String image) {
        this.id = id;
        this.image = image;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
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
        if (!(object instanceof ProductImageDto)) {
            return false;
        }
        ProductImageDto other = (ProductImageDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.ProductImageDto[ id=" + id + " ]";
    }
    
}
