/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.CategoryDto;
import com.lacv.marketplatform.dtos.SubCategoryDto;
import com.lacv.marketplatform.entities.SubCategory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("subCategoryMapper")
public class SubCategoryMapper implements BasicEntityMapper {

    @Autowired
    CategoryMapper categoryMapper;
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        SubCategory entity= (SubCategory) baseEntity;
        SubCategoryDto dto= new SubCategoryDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setImage(entity.getImage());
            dto.setName(entity.getName());
            dto.setCategory((CategoryDto) categoryMapper.entityToDto(entity.getCategory()));
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
        ArrayList<SubCategoryDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((SubCategoryDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
