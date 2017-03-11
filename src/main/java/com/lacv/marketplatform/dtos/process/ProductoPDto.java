/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.HideField;
import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.enums.HideView;
import java.util.List;

/**
 *
 * @author grupot
 */
public class ProductoPDto {
    
    @Order(1)
    @NotNull
    private String nombre;
    
    @Order(2)
    @HideField(HideView.LOG)
    @NotNull
    private Integer precio;
    
    @Order(3)
    private Integer cantidad;
    
    private Boolean activo;
    
    @TextField("C&oacute;digo de Barras")
    private Long codigoDeBarra;
    
    private List<TagPDto> tags;
    
    
    public ProductoPDto(){
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public Integer getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(Long codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public List<TagPDto> getTags() {
        return tags;
    }

    public void setTags(List<TagPDto> tags) {
        this.tags = tags;
    }
    
}
