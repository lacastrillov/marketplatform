/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.CommerceDto;
import com.lacv.marketplatform.dtos.MainLocationDto;
import com.lacv.marketplatform.entities.Commerce;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("commerceMapper")
public class CommerceMapper implements BasicEntityMapper {
    
    @Autowired
    MainLocationMapper mainLocationMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Commerce entity= (Commerce) baseEntity;
        CommerceDto dto= new CommerceDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setCommerceDescription(entity.getCommerceDescription());
            dto.setCommerceImage(entity.getCommerceImage());
            dto.setCommerceName(entity.getCommerceName());
            dto.setCommerceTag(entity.getCommerceTag());
            dto.setCreationDate(entity.getCreationDate());
            dto.setMainLocation((MainLocationDto) mainLocationMapper.entityToDto(entity.getMainLocation()));
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
        ArrayList<CommerceDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((CommerceDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
