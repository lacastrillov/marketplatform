/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.LogProcessDto;
import com.lacv.marketplatform.entities.LogProcess;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("logProcessMapper")
public class LogProcessMapper implements BasicEntityMapper {
    
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        LogProcess entity= (LogProcess) baseEntity;
        LogProcessDto dto= new LogProcessDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setClientId(entity.getClientId());
            dto.setDataIn(entity.getDataIn());
            dto.setDataOut(entity.getDataOut());
            dto.setDuration(entity.getDuration());
            dto.setMainProcessRef(entity.getMainProcessRef());
            dto.setMessage(entity.getMessage());
            dto.setProcessName(entity.getProcessName());
            dto.setRecordTime(entity.getRecordTime());
            dto.setRegistrationDate(entity.getRegistrationDate());
            dto.setSuccess(entity.getSuccess());
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
        ArrayList<LogProcessDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((LogProcessDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
