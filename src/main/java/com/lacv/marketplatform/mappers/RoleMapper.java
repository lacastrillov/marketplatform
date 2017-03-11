/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.lacv.marketplatform.dtos.RoleDto;
import com.lacv.marketplatform.entities.Role;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("roleMapper")
public class RoleMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Role entity= (Role) baseEntity;
        RoleDto dto= new RoleDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
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
        ArrayList<RoleDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((RoleDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
