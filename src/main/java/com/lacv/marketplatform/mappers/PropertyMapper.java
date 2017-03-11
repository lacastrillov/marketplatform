/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.PropertyDto;
import com.lacv.marketplatform.entities.Property;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("propertyMapper")
public class PropertyMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Property entity= (Property) baseEntity;
        PropertyDto dto= new PropertyDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setKey(entity.getKey());
            dto.setStatus(entity.getStatus());
            dto.setType(entity.getType());
            dto.setValue(entity.getValue());
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
        ArrayList<PropertyDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((PropertyDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
