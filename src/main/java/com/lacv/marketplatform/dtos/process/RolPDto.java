/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;
import java.util.List;

/**
 *
 * @author grupot
 */
public class RolPDto {
    
    @Order(1)
    @NotNull
    private String nombre;
    
    @Order(2)
    @TypeFormField(value = FieldType.LIST, data = {"Active", "Inactive", "Locked", "Deleted"})
    private String estado;
    
    private List<AutorizacionPDto> autorizaciones;
    
    
    public RolPDto(){
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the autorizaciones
     */
    public List<AutorizacionPDto> getAutorizaciones() {
        return autorizaciones;
    }

    /**
     * @param autorizaciones the autorizaciones to set
     */
    public void setAutorizaciones(List<AutorizacionPDto> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }
    
    
}
