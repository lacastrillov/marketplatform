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
import java.util.List;

/**
 *
 * @author grupot
 */
public class ContactUserPDto {
    
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
    
    @Order(4)
    @TextField("Destinatarios")
    private List<String> recipients;
    
    
    public ContactUserPDto(){
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

    /**
     * @return the recipients
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
    
}
