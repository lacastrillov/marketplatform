/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

/**
 *
 * @author grupot
 */
public class ResultadoPDto {
    
    private Boolean success;
    
    private String message;
    
    private MainLocationPDto mainLocation;

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public MainLocationPDto getMainLocation() {
        return mainLocation;
    }

    public void setMainLocation(MainLocationPDto mainLocation) {
        this.mainLocation = mainLocation;
    }
    
}
