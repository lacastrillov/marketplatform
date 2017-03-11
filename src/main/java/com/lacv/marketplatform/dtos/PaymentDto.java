/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.ReadOnly;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;
import java.util.Date;

/**
 *
 * @author lacastrillov
 */
public class PaymentDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @Order(2)
    @TextField("Numero de Referencia")
    private String referenceNumber;
    
    @Order(3)
    @TextField("Valor")
    private Integer amount;
    
    @TextField("Fecha")
    private Date paymentDate;
    
    @TextField("Medio de Pago")
    private String paymentMethod;
    
    @TextField("Resultado")
    @TypeFormField(value = FieldType.LIST, data = {"Pendiente", "Aprobado", "Rechazado", "Error"})
    private String paymentResult;
    
    @TextField("Usuario")
    private UserDto user;
    
    @TextField("Orden de Compra")
    private PurchaseOrderDto purchaseOrder;
    
    @TextField("Orden de Inventario")
    private InventoryOrderDto inventoryOrder;

    public PaymentDto() {
    }

    public PaymentDto(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(String paymentResult) {
        this.paymentResult = paymentResult;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PurchaseOrderDto getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderDto purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public InventoryOrderDto getInventoryOrder() {
        return inventoryOrder;
    }

    public void setInventoryOrder(InventoryOrderDto inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
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
        if (!(object instanceof PaymentDto)) {
            return false;
        }
        PaymentDto other = (PaymentDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.PaymentDto[ id=" + id + " ]";
    }
    
}
