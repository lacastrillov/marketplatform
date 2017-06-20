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
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author grupot
 */
public class ProductImageReportDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    private Integer id;
    
    @Order(2)
    @TextField("Imagen")
    @TypeFormField(FieldType.IMAGE_FILE_UPLOAD)
    @ColumnWidth(300)
    private String image;
    
    @Order(3)
    @TextField("Orden")
    private Integer order_level;
    
    @Order(4)
    @TextField("Id Producto")
    private Integer product_id;

    public ProductImageReportDto() {
    }

    public ProductImageReportDto(Integer id) {
        this.id = id;
    }

    public ProductImageReportDto(Integer id, String image) {
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

    public Integer getOrder_level() {
        return order_level;
    }

    public void setOrder_level(Integer order_level) {
        this.order_level = order_level;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
        if (!(object instanceof ProductImageReportDto)) {
            return false;
        }
        ProductImageReportDto other = (ProductImageReportDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.ProductImageReportDto[ id=" + id + " ]";
    }
    
}
