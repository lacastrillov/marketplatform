/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.domain.BaseEntity;
import java.util.List;

/**
 *
 * @author grupot
 */
public class AuthorizationDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String name;
    
    private String description;
    
    private List<RoleAuthorizationDto> roleAuthorizationList;
    
    private List<WebresourceAuthorizationDto> webresourceAuthorizationList;

    public AuthorizationDto() {
    }

    public AuthorizationDto(Integer id) {
        this.id = id;
    }

    public AuthorizationDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoleAuthorizationDto> getRoleAuthorizationList() {
        return roleAuthorizationList;
    }

    public void setRoleAuthorizationList(List<RoleAuthorizationDto> roleAuthorizationList) {
        this.roleAuthorizationList = roleAuthorizationList;
    }

    public List<WebresourceAuthorizationDto> getWebresourceAuthorizationList() {
        return webresourceAuthorizationList;
    }

    public void setWebresourceAuthorizationList(List<WebresourceAuthorizationDto> webresourceAuthorizationList) {
        this.webresourceAuthorizationList = webresourceAuthorizationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorizationDto)) {
            return false;
        }
        AuthorizationDto other = (AuthorizationDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.AuthorizationDto[ id=" + id + " ]";
    }
    
}
