/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;
import java.util.Date;

/**
 *
 * @author grupot
 */
public class UsuarioPDto {
    
    @Order(1)
    @NotNull
    private String nombre;
    
    @Order(2)
    @NotNull
    @TypeFormField(FieldType.EMAIL)
    private String correo;
    
    @Order(3)
    private Long telefono;
    
    @Order(4)
    @TextField("Fecha Registro")
    private Date fechaRegistro;
    
    @Order(5)
    private RolPDto rol;

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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public Long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the rol
     */
    public RolPDto getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(RolPDto rol) {
        this.rol = rol;
    }
    
}
