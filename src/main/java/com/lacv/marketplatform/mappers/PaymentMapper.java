/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.InventoryOrderDto;
import com.lacv.marketplatform.dtos.PaymentDto;
import com.lacv.marketplatform.dtos.PurchaseOrderDto;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.Payment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("paymentMapper")
public class PaymentMapper implements BasicEntityMapper {
    
    @Autowired
    InventoryOrderMapper inventoryOrderMapper;
    
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    
    @Autowired
    UserMapper userMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Payment entity= (Payment) baseEntity;
        PaymentDto dto= new PaymentDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setAmount(entity.getAmount());
            dto.setInventoryOrder((InventoryOrderDto) inventoryOrderMapper.entityToDto(entity.getInventoryOrder()));
            dto.setPaymentDate(entity.getPaymentDate());
            dto.setPaymentMethod(entity.getPaymentMethod());
            dto.setPaymentResult(entity.getPaymentResult());
            dto.setPurchaseOrder((PurchaseOrderDto) purchaseOrderMapper.entityToDto(entity.getPurchaseOrder()));
            dto.setReferenceNumber(entity.getReferenceNumber());
            dto.setUser((UserDto) userMapper.entityToDto(entity.getUser()));
        }
        return dto;
    }
    
    /**
     *
     * @param entities
     * @return
     */
    @Override
    public List<? extends BaseEntity> listEntitiesToListDtos(List <? extends BaseEntity> entities){
        ArrayList<PaymentDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((PaymentDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
