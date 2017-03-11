/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.CategoryDto;
import com.lacv.marketplatform.entities.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("categoryMapper")
public class CategoryMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Category entity= (Category) baseEntity;
        CategoryDto dto= new CategoryDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setImage(entity.getImage());
            dto.setName(entity.getName());
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
        ArrayList<CategoryDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((CategoryDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
