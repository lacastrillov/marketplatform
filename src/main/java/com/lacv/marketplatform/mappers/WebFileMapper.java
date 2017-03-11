/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.WebFileDto;
import com.lacv.marketplatform.entities.WebFile;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("webFileMapper")
public class WebFileMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        WebFile entity= (WebFile) baseEntity;
        WebFileDto dto= new WebFileDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setAccess(entity.getAccess());
            dto.setAuthor(entity.getAuthor());
            dto.setCreationDate(entity.getCreationDate());
            dto.setIcon(entity.getIcon());
            dto.setLocation(entity.getLocation());
            dto.setPath(entity.getPath());
            dto.setModificationDate(entity.getModificationDate());
            dto.setName(entity.getName());
            dto.setSize(entity.getSize());
            dto.setType(entity.getType());
            dto.setWebFile((WebFileDto) entityToDto(entity.getWebFile()));
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
        ArrayList<WebFileDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((WebFileDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
