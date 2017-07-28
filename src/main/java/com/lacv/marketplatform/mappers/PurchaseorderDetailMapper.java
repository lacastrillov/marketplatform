/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.dtos.PurchaseOrderDto;
import com.lacv.marketplatform.dtos.PurchaseorderDetailDto;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("purchaseorderDetailMapper")
public class PurchaseorderDetailMapper implements BasicEntityMapper {
    
    @Autowired
    ProductMapper productMapper;
    
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        PurchaseorderDetail entity= (PurchaseorderDetail) baseEntity;
        PurchaseorderDetailDto dto= new PurchaseorderDetailDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setProduct((ProductDto) productMapper.entityToDto(entity.getProduct()));
            dto.setPurchaseOrder((PurchaseOrderDto) purchaseOrderMapper.entityToDto(entity.getPurchaseOrder()));
            dto.setQuantity(entity.getQuantity());
            dto.setUnitPrice(entity.getUnitPrice());
            dto.setSubTotal(entity.getSubTotal());
            dto.setDiscount(entity.getDiscount());
            dto.setTotal(entity.getTotal());
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
        ArrayList<PurchaseorderDetailDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((PurchaseorderDetailDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
