/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.Size;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author grupot
 */
public class MainLocationPDto {
    
    @Order(1)
    @ColumnWidth(100)
    private Integer id;
    
    private String uuid;
    
    @Size(max=100)
    @Order(2)
    @TextField("Ml Name")
    private String mlName;
    
    @Order(3)
    @Size(max=500)
    @TypeFormField(FieldType.TEXT_AREA)
    @TextField("Ml Description")
    private String mlDescription;
    
    @Order(4)
    @Size(max=200)
    @TextField("Ml Location")
    @TypeFormField(FieldType.GOOGLE_MAP)
    private String mlLocation;
    
    @Order(5)
    private UsuarioPDto usuario;
    
    @Order(6)
    private ProductoPDto producto;
    

    public MainLocationPDto() {
    }

    public MainLocationPDto(Integer idMainLocation) {
        this.id = idMainLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMlDescription() {
        return mlDescription;
    }

    public void setMlDescription(String mlDescription) {
        this.mlDescription = mlDescription;
    }

    public String getMlLocation() {
        return mlLocation;
    }

    public void setMlLocation(String mlLocation) {
        this.mlLocation = mlLocation;
    }

    public String getMlName() {
        return mlName;
    }

    public void setMlName(String mlName) {
        this.mlName = mlName;
    }

    public UsuarioPDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPDto usuario) {
        this.usuario = usuario;
    }

    public ProductoPDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoPDto producto) {
        this.producto = producto;
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
        if (!(object instanceof MainLocationPDto)) {
            return false;
        }
        MainLocationPDto other = (MainLocationPDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.MainLocationPDto[ idMainLocation=" + id + " ]";
    }
    
}
