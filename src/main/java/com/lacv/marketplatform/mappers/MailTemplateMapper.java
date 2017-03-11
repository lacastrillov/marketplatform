/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.MailTemplateDto;
import com.lacv.marketplatform.entities.MailTemplate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("mailTemplateMapper")
public class MailTemplateMapper implements BasicEntityMapper {

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        MailTemplate entity= (MailTemplate) baseEntity;
        MailTemplateDto dto= new MailTemplateDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setAlias(entity.getAlias());
            dto.setContent(entity.getContent());
            dto.setName(entity.getName());
            dto.setStatus(entity.getStatus());
            dto.setTotalSent(entity.getTotalSent());
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
        ArrayList<MailTemplateDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((MailTemplateDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
