/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.AuthorizationDto;
import com.lacv.marketplatform.dtos.WebResourceDto;
import com.lacv.marketplatform.dtos.WebresourceAuthorizationDto;
import com.lacv.marketplatform.entities.WebresourceAuthorization;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("webresourceAuthorizationMapper")
public class WebresourceAuthorizationMapper implements BasicEntityMapper {

    @Autowired
    AuthorizationMapper authorizationMapper;
    
    @Autowired
    WebResourceMapper webResourceMapper;
    
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        WebresourceAuthorization entity= (WebresourceAuthorization) baseEntity;
        WebresourceAuthorizationDto dto= new WebresourceAuthorizationDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setAuthorization((AuthorizationDto) authorizationMapper.entityToDto(entity.getAuthorization()));
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
        ArrayList<WebresourceAuthorizationDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((WebresourceAuthorizationDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
