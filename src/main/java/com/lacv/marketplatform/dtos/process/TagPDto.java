/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author grupot
 */
public class TagPDto {
    
    private Integer codigo;
    
    @NotNull
    private String nombre;
    
    @TypeFormField(value = FieldType.LIST, data = {"Active", "Inactive", "Locked", "Deleted"})
    private String estado;
    
    
    public TagPDto(){
    }
    
    public TagPDto(String nombre){
        this.nombre= nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
