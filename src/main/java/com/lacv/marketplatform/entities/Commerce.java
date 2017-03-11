/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.entities;

import com.dot.gcpbasedot.domain.BaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author grupot
 */
@Entity
@Table(name = "commerce")
@NamedQueries({
    @NamedQuery(name = "Commerce.findAll", query = "SELECT c FROM Commerce c")})
public class Commerce implements BaseEntity {

    @OneToMany(mappedBy = "commerce")
    private List<Product> productList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commerce_id")
    private Integer id;
    @Size(max = 500)
    @Column(name = "commerce_description")
    private String commerceDescription;
    @Size(max = 200)
    @Column(name = "commerce_image")
    private String commerceImage;
    @Size(max = 100)
    @Column(name = "commerce_name")
    @NotNull
    private String commerceName;
    @Size(max = 200)
    @Column(name = "commerce_tag")
    private String commerceTag;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "idMainLocation", referencedColumnName = "idMainLocation")
    @ManyToOne
    private MainLocation mainLocation;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
    @Transient
    protected Object[] jdoDetachedState;

    public Commerce() {
    }

    public Commerce(Integer commerceId) {
        this.id = commerceId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getCommerceDescription() {
        return commerceDescription;
    }

    public void setCommerceDescription(String commerceDescription) {
        this.commerceDescription = commerceDescription;
    }

    public String getCommerceImage() {
        return commerceImage;
    }

    public void setCommerceImage(String commerceImage) {
        this.commerceImage = commerceImage;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getCommerceTag() {
        return commerceTag;
    }

    public void setCommerceTag(String commerceTag) {
        this.commerceTag = commerceTag;
    }

    public MainLocation getMainLocation() {
        return mainLocation;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setMainLocation(MainLocation mainLocation) {
        this.mainLocation = mainLocation;
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
        if (!(object instanceof Commerce)) {
            return false;
        }
        Commerce other = (Commerce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.Commerce[ commerceId=" + id + " ]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
}
