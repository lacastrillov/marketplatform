/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.SupplierDto;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.entities.Supplier;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("supplierMapper")
public class SupplierMapper implements BasicEntityMapper {
    
    @Autowired
    UserMapper userMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Supplier entity= (Supplier) baseEntity;
        SupplierDto dto= new SupplierDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setAddress(entity.getAddress());
            dto.setCity(entity.getCity());
            dto.setCompanyName(entity.getCompanyName());
            dto.setContactName(entity.getContactName());
            dto.setContactTitle(entity.getContactTitle());
            dto.setCountry(entity.getCountry());
            dto.setMail(entity.getMail());
            dto.setPhoneMobile(entity.getPhoneMobile());
            dto.setPhoneOffice(entity.getPhoneOffice());
            dto.setRegion(entity.getRegion());
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
        ArrayList<SupplierDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((SupplierDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
