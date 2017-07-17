/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.mappers;

import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.mapper.BasicEntityMapper;
import com.lacv.marketplatform.dtos.LeadTableDto;
import com.lacv.marketplatform.dtos.TableColumnDto;
import com.lacv.marketplatform.entities.TableColumn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nalvarez
 */
@Component("tableColumnMapper")
public class TableColumnMapper implements BasicEntityMapper {

    @Autowired
    LeadTableMapper leadTableMapper;
    
    @Override
    public BaseEntity entityToDto(BaseEntity baseEntity) {
        TableColumn entity= (TableColumn) baseEntity;
        TableColumnDto dto= new TableColumnDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setColumnAlias(entity.getColumnAlias());
            dto.setColumnOrder(entity.getColumnOrder());
            dto.setColumnSize(entity.getColumnSize());
            dto.setDataType(entity.getDataType());
            dto.setDefaultValue(entity.getDefaultValue());
            dto.setFieldType(entity.getFieldType());
            dto.setName(entity.getName());
            dto.setOptions(entity.getOptions());
            dto.setWidth(entity.getWidth());
            dto.setLeadTable((LeadTableDto) leadTableMapper.entityToDto(entity.getLeadTable()));
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
        ArrayList<TableColumnDto> dtos= new ArrayList<>();
        if(entities!=null){
            for(BaseEntity entity: entities){
                dtos.add((TableColumnDto) entityToDto(entity));
            }
        }
        return dtos;
    }
    
}
