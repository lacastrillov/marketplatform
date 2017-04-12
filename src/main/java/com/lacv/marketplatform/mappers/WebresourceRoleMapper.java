/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.RoleDto;
import com.lacv.marketplatform.dtos.WebResourceDto;
import com.lacv.marketplatform.dtos.WebresourceRoleDto;
import com.lacv.marketplatform.entities.WebresourceRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("webresourceRoleMapper")
public class WebresourceRoleMapper implements BasicEntityMapper {
    
    @Autowired
    RoleMapper roleMapper;
    
    @Autowired
    WebResourceMapper webResourceMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        WebresourceRole entity= (WebresourceRole) baseEntity;
        WebresourceRoleDto dto= new WebresourceRoleDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setRole((RoleDto) roleMapper.entityToDto(entity.getRole()));
            dto.setWebResource((WebResourceDto) webResourceMapper.entityToDto(entity.getWebResource()));
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
        ArrayList<WebresourceRoleDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((WebresourceRoleDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
