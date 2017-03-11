/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.MainLocationDto;
import com.lacv.marketplatform.entities.MainLocation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("mainLocationMapper")
public class MainLocationMapper implements BasicEntityMapper {
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        MainLocation entity= (MainLocation) baseEntity;
        MainLocationDto dto= new MainLocationDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setMlDescription(entity.getMlDescription());
            dto.setMlLocation(entity.getMlLocation());
            dto.setMlName(entity.getMlName());
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
        ArrayList<MainLocationDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((MainLocationDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
