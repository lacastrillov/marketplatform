/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.InventoryOrderDto;
import com.lacv.marketplatform.dtos.SupplierDto;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.InventoryOrder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("inventoryOrderMapper")
public class InventoryOrderMapper implements BasicEntityMapper {
    
    @Autowired
    SupplierMapper supplierMapper;
    
    @Autowired
    UserMapper userMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        InventoryOrder entity= (InventoryOrder) baseEntity;
        InventoryOrderDto dto= new InventoryOrderDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setNumber(entity.getNumber());
            dto.setRecordTime(entity.getRecordTime());
            dto.setRegistrationDate(entity.getRegistrationDate());
            dto.setStatus(entity.getStatus());
            dto.setSupplier((SupplierDto) supplierMapper.entityToDto(entity.getSupplier()));
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
        ArrayList<InventoryOrderDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((InventoryOrderDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
