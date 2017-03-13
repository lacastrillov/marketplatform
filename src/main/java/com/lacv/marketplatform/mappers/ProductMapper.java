/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.CategoryDto;
import com.lacv.marketplatform.dtos.CommerceDto;
import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.dtos.SubCategoryDto;
import com.lacv.marketplatform.dtos.SupplierDto;
import com.lacv.marketplatform.entities.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("productMapper")
public class ProductMapper implements BasicEntityMapper {
    
    @Autowired
    CategoryMapper categoryMapper;
    
    @Autowired
    SubCategoryMapper subCategoryMapper;
    
    @Autowired
    SupplierMapper supplierMapper;
    
    @Autowired
    CommerceMapper commerceMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Product entity= (Product) baseEntity;
        ProductDto dto= new ProductDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setBrand(entity.getBrand());
            dto.setBuyUnitPrice(entity.getBuyUnitPrice());
            dto.setCategory((CategoryDto) categoryMapper.entityToDto(entity.getCategory()));
            dto.setSubCategory((SubCategoryDto) subCategoryMapper.entityToDto(entity.getSubCategory()));
            dto.setCode(entity.getCode());
            dto.setDescription(entity.getDescription());
            dto.setDiscount(entity.getDiscount());
            dto.setFeatured(entity.getFeatured());
            dto.setKeywords(entity.getKeywords());
            dto.setName(entity.getName());
            dto.setOrderLevel(entity.getOrderLevel());
            dto.setQuantityPerUnit(entity.getQuantityPerUnit());
            dto.setRegisterDate(entity.getRegisterDate());
            dto.setSeggestedUnitPrice(entity.getSeggestedUnitPrice());
            dto.setStatus(entity.getStatus());
            dto.setSupplier((SupplierDto) supplierMapper.entityToDto(entity.getSupplier()));
            dto.setUnitsInOrder(entity.getUnitsInOrder());
            dto.setUnitsInStock(entity.getUnitsInStock());
            dto.setCommerce((CommerceDto) commerceMapper.entityToDto(entity.getCommerce()));
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
        ArrayList<ProductDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((ProductDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
