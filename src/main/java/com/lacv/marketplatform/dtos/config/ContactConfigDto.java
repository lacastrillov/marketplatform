/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.config;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;
import com.lacv.marketplatform.dtos.process.TagPDto;
import com.lacv.marketplatform.dtos.process.UsuarioPDto;
import java.util.List;

/**
 *
 * @author grupot
 */
public class ContactConfigDto {
    
    @Order(1)
    @NotNull
    @TextField("Nombre de Usuario")
    private String userName;
    
    @Order(2)
    @NotNull
    @TextField("Correo")
    @TypeFormField(FieldType.EMAIL)
    private String mail;
    
    @Order(3)
    @TextField("Celular")
    private String cellPhone;
    
    @Order(4)
    @TextField("Comentarios")
    @TypeFormField(FieldType.TEXT_AREA)
    private String comments;
    
    @TextField("Usuario")
    private UsuarioPDto usuario;
    
    private List<TagPDto> tags;
    
    
    public ContactConfigDto(){
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UsuarioPDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPDto usuario) {
        this.usuario = usuario;
    }

    public List<TagPDto> getTags() {
        return tags;
    }

    public void setTags(List<TagPDto> tags) {
        this.tags = tags;
    }
    
}
