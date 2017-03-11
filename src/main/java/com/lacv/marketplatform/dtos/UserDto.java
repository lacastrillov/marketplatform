/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.HideField;
import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.ReadOnly;
import com.dot.gcpbasedot.annotation.Size;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;
import com.dot.gcpbasedot.enums.HideView;
import java.util.Date;

/**
 *
 * @author grupot
 */
public class UserDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @ColumnWidth(100)
    @Order(1)
    private Integer id;
    
    @Size(max=100)
    @Order(2)
    @NotNull
    @TextField("Nombre")
    private String name;
    
    @Size(max=100)
    @TypeFormField(FieldType.EMAIL)
    @Order(3)
    @NotNull
    @TextField("Correo")
    private String email;
    
    @Size(max=100)
    @Order(4)
    @TextField("Usuario")
    private String username;
    
    @Size(max=60)
    @TypeFormField(FieldType.PASSWORD)
    @HideField({HideView.FILTER, HideView.GRID})
    @Order(5)
    @ReadOnly
    @TextField("Contrase&ntilde;a")
    private String password;
    
    @Size(max=1)
    @TypeFormField(value = FieldType.LIST, data = {"F", "M"})
    @TextField("Genero")
    private String gender;
    
    @Size(max=200)
    @TypeFormField(FieldType.URL)
    @HideField({HideView.FILTER})
    @TextField("P&aacute;gina")
    private String link;
    
    @Size(max=200)
    @TypeFormField(FieldType.IMAGE_FILE_UPLOAD)
    @HideField({HideView.FILTER})
    @TextField("Foto perfil")
    private String urlPhoto;
    
    @TextField("Fecha Nacimieto")
    private Date birthday;
    
    @TextField("Token Usuario")
    @Size(max=200)
    @HideField({HideView.FILTER, HideView.FORM})
    private String tokenUser;
    
    @Size(max=45)
    @TypeFormField(value = FieldType.LIST, data = {"Active", "Inactive", "Locked", "Deleted"})
    @TextField("Estado")
    private String status;
    
    @TextField("Verificado")
    private Boolean verified;
    
    @TextField("Intentos fallidos")
    private Integer failedAttempts;
    
    @TextField("Expiraci&oacute;n contrase&ntilde;a")
    private Date passwordExpiration;
    
    @ReadOnly
    @TextField("Ultimo login")
    private Date lastLogin;
    

    public UserDto() {
    }

    public UserDto(Integer id) {
        this.id = id;
    }

    public UserDto(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrlPhoto() {
        return urlPhoto;
    }
    
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Date getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Date passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
        if (!(object instanceof UserDto)) {
            return false;
        }
        UserDto other = (UserDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.UserDto[ id=" + id + " ]";
    }
    
}
