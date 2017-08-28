/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.Size;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author grupot
 */
public class RegisterUserPDto {
    
    @Order(1)
    @Size(max=100)
    @NotNull
    @TextField("Nombre")
    private String firstName;
    
    @Order(2)
    @Size(max=100)
    @NotNull
    @TextField("Apellidos")
    private String lastName;
    
    @Order(3)
    @Size(max=100)
    @TextField("Celular")
    private String cell;
    
    @Order(4)
    @Size(max=100)
    @TypeFormField(FieldType.EMAIL)
    @NotNull
    @TextField("Correo")
    private String email;
    
    @Order(5)
    @Size(max=60)
    @TypeFormField(FieldType.PASSWORD)
    @TextField("Contrase&ntilde;a")
    private String password;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * @param cell the cell to set
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
