/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.WebResourceDto;
import com.lacv.marketplatform.entities.WebResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("webResourceMapper")
public class WebResourceMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        WebResource entity= (WebResource) baseEntity;
        WebResourceDto dto= new WebResourceDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setIsPublic(entity.getIsPublic());
            dto.setName(entity.getName());
            dto.setPath(entity.getPath());
            dto.setType(entity.getType());
            dto.setCategory(entity.getCategory());
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
        ArrayList<WebResourceDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((WebResourceDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
