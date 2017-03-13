/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.dtos.ProductImageDto;
import com.lacv.marketplatform.entities.ProductImage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("productImageMapper")
public class ProductImageMapper implements BasicEntityMapper {

    @Autowired
    ProductMapper productMapper;
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        ProductImage entity= (ProductImage) baseEntity;
        ProductImageDto dto= new ProductImageDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setImage(entity.getImage());
            dto.setOrder(entity.getOrder());
            dto.setProduct((ProductDto) productMapper.entityToDto(entity.getProduct()));
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
        ArrayList<ProductImageDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((ProductImageDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
