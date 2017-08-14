/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.HideField;
import com.dot.gcpbasedot.annotation.ImageResize;
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
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @Order(2)
    @Size(max=100)
    @NotNull
    @TextField("Nombre")
    private String firstName;
    
    @Order(3)
    @Size(max=100)
    @NotNull
    @TextField("Apellidos")
    private String lastName;
    
    @Order(4)
    @TextField("Documento Id")
    private Long idDocument;
    
    @Order(5)
    @TypeFormField(value = FieldType.LIST, data = {"CC:C&eacute;dula de ciudadania","TI:Tarjeta de identidad","CE:C&eacute;dula de Extranjeria","NIT:NIT","PAS:Pasaporte"})
    @TextField("Tipo Documento")
    private String documentType;
    
    @Order(6)
    @Size(max=100)
    @TextField("Tel&eacute;fono")
    private String phone;
    
    @Order(7)
    @Size(max=100)
    @TextField("Celular")
    private String cell;
    
    @Order(8)
    @Size(max=100)
    @TypeFormField(FieldType.EMAIL)
    @NotNull
    @TextField("Correo")
    private String email;
    
    @Order(9)
    @Size(max=100)
    @TextField("Usuario")
    private String username;
    
    @Order(10)
    @Size(max=60)
    @TypeFormField(FieldType.PASSWORD)
    @HideField({HideView.FILTER, HideView.GRID})
    @ReadOnly
    @TextField("Contrase&ntilde;a")
    private String password;
    
    @Order(11)
    @HideField({HideView.FILTER, HideView.GRID})
    @TextField("Direcci&oacute;n")
    private String address;
    
    @Order(12)
    @TextField("Ciudad")
    private String city;
    
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
    @ImageResize({"300,300", "500,500", "800,800"})
    @HideField({HideView.GRID, HideView.FILTER})
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
