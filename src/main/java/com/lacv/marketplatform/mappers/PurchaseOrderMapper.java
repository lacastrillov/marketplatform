/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.PurchaseOrderDto;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("purchaseOrderMapper")
public class PurchaseOrderMapper implements BasicEntityMapper {
    
    @Autowired
    UserMapper userMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        PurchaseOrder entity= (PurchaseOrder) baseEntity;
        PurchaseOrderDto dto= new PurchaseOrderDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setNumber(entity.getNumber());
            dto.setRecordTime(entity.getRecordTime());
            dto.setRegistrationDate(entity.getRegistrationDate());
            dto.setStatus(entity.getStatus());
            dto.setSubTotal(entity.getSubTotal());
            dto.setDiscount(entity.getDiscount());
            dto.setTotal(entity.getTotal());
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
        ArrayList<PurchaseOrderDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((PurchaseOrderDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
