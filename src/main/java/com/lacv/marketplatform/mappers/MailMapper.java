/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.MailDto;
import com.lacv.marketplatform.dtos.MailTemplateDto;
import com.lacv.marketplatform.entities.Mail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("mailMapper")
public class MailMapper implements BasicEntityMapper {
    
    @Autowired
    MailTemplateMapper mailTemplateMapper;

    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        Mail entity= (Mail) baseEntity;
        MailDto dto= new MailDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setMailFrom(entity.getMailFrom());
            dto.setMailTemplate((MailTemplateDto) mailTemplateMapper.entityToDto(entity.getMailTemplate()));
            dto.setMailTo(entity.getMailTo());
            dto.setMessage(entity.getMessage());
            dto.setResult(entity.getResult());
            dto.setSendDate(entity.getSendDate());
            dto.setSubject(entity.getSubject());
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
        ArrayList<MailDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((MailDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
