/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.HideField;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.Size;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.enums.FieldType;
import com.dot.gcpbasedot.enums.HideView;

/**
 *
 * @author grupot
 */
public class CreatePasswordDto {
    
    @Size(max=100)
    @Order(1)
    private String username;
    
    @Size(max=60)
    @TypeFormField(FieldType.PASSWORD)
    @HideField(HideView.LOG)
    @Order(2)
    private String password;
    
    @Order(3)
    @Size(max=60)
    @TypeFormField(FieldType.PASSWORD)
    @HideField(HideView.LOG)
    @TextField("Confirm Password")
    private String confirmPassword;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
