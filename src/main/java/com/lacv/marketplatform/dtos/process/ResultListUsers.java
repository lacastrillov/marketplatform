/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.lacv.marketplatform.dtos.UserDto;
import java.util.List;

/**
 *
 * @author lacastrillov
 */
public class ResultListUsers {

    private boolean success;

    private String message;

    private List<UserDto> users;

    private Long totalCount;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
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

    /**
     * @return the users
     */
    public List<UserDto> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    /**
     * @return the totalCount
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
