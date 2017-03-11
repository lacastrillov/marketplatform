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
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lacastrillov
 */
public class InventoryOrderDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Order(1)
    @ColumnWidth(100)
    @ReadOnly
    private Integer id;
    
    @TextField("N&uacute;mero")
    private Long number;
    
    @TextField("Fecha Registro")
    private Date registrationDate;
    
    @TextField("Hora Registro")
    private Time recordTime;
    
    @TextField("Total")
    private Integer total;
    
    @TextField("Estado")
    @TypeFormField(value = FieldType.LIST, data = {"Pendiente", "Cancelada", "Pagada", "Rechazada"})
    private String status;
    
    private List<InventoryorderDetailDto> inventoryorderDetailList;
    
    @TextField("Usuario")
    private UserDto user;
    
    @TextField("Proveedor")
    private SupplierDto supplier;
    
    private List<PaymentDto> paymentList;

    public InventoryOrderDto() {
    }

    public InventoryOrderDto(Integer id) {
        this.id = id;
    }

    public InventoryOrderDto(Integer id, long number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Time getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Time recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InventoryorderDetailDto> getInventoryorderDetailList() {
        return inventoryorderDetailList;
    }

    public void setInventoryorderDetailList(List<InventoryorderDetailDto> inventoryorderDetailList) {
        this.inventoryorderDetailList = inventoryorderDetailList;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }

    public List<PaymentDto> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentDto> paymentList) {
        this.paymentList = paymentList;
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
        if (!(object instanceof InventoryOrderDto)) {
            return false;
        }
        InventoryOrderDto other = (InventoryOrderDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.InventoryOrderDto[ id=" + id + " ]";
    }
    
}
