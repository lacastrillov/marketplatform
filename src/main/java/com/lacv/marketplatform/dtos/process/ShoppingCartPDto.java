/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.TextField;
import java.util.List;

/**
 *
 * @author lacastrillov
 */
public class ShoppingCartPDto {
    
    @Order(1)
    @TextField("Id Usuario")
    private Integer userId;
    
    private List<ShippingCartItemPDto> items;
    
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ShippingCartItemPDto> getItems() {
        return items;
    }

    public void setItems(List<ShippingCartItemPDto> items) {
        this.items = items;
    }
    
}
