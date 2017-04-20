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
public class WebResourceDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String name;
    
    private String path;
    
    private String type;
    
    private Boolean isPublic;
    
    private List<WebresourceAuthorizationDto> webresourceAuthorizationList;
    
    private List<WebresourceRoleDto> webresourceRoleList;

    public WebResourceDto() {
    }

    public WebResourceDto(Integer id) {
        this.id = id;
    }

    public WebResourceDto(Integer id, String name, String pah) {
        this.id = id;
        this.name = name;
        this.path = pah;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public List<WebresourceAuthorizationDto> getWebresourceAuthorizationList() {
        return webresourceAuthorizationList;
    }

    public void setWebresourceAuthorizationList(List<WebresourceAuthorizationDto> webresourceAuthorizationList) {
        this.webresourceAuthorizationList = webresourceAuthorizationList;
    }

    public List<WebresourceRoleDto> getWebresourceRoleList() {
        return webresourceRoleList;
    }

    public void setWebresourceRoleList(List<WebresourceRoleDto> webresourceRoleList) {
        this.webresourceRoleList = webresourceRoleList;
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
        if (!(object instanceof WebResourceDto)) {
            return false;
        }
        WebResourceDto other = (WebResourceDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.WebResourceDto[ id=" + id + " ]";
    }
    
}
