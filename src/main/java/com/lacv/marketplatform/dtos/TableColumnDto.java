/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos;

import com.dot.gcpbasedot.annotation.ColumnWidth;
import com.dot.gcpbasedot.annotation.DefaultValue;
import com.dot.gcpbasedot.annotation.NotNull;
import com.dot.gcpbasedot.annotation.Order;
import com.dot.gcpbasedot.annotation.ReadOnly;
import com.dot.gcpbasedot.annotation.Size;
import com.dot.gcpbasedot.annotation.TextField;
import com.dot.gcpbasedot.annotation.TypeFormField;
import com.dot.gcpbasedot.domain.BaseEntity;
import com.dot.gcpbasedot.enums.FieldType;

/**
 *
 * @author grupot
 */
public class TableColumnDto implements BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @ColumnWidth(100)
    @Order(1)
    @ReadOnly
    private Integer id;
    
    @Size(max=100)
    @Order(2)
    @NotNull
    @TextField("Nombre")
    private String name;
    
    @Size(max=100)
    @Order(3)
    @NotNull
    @TextField("Alias")
    private String columnAlias;
    
    @Size(max=45)
    @Order(4)
    @NotNull
    @ColumnWidth(150)
    @TypeFormField(value = FieldType.LIST, data = {"java.lang.String", "char", "int", "long", "double", "float", "boolean", "java.util.Date", "java.sql.Time"})
    @TextField("Tipo Dato")
    private String dataType;
    
    @Size(max=45)
    @Order(5)
    @TypeFormField(value = FieldType.LIST, data = {
        "EMAIL", "PASSWORD", "TEXT_AREA", "LIST", "URL", "HTML_EDITOR", "FILE_UPLOAD", "IMAGE_FILE_UPLOAD",
        "VIDEO_YOUTUBE", "VIDEO_FILE_UPLOAD", "AUDIO_FILE_UPLOAD", "GOOGLE_MAP", "MULTI_FILE_TYPE"
    })
    @TextField("Tipo Campo")
    private String fieldType;
    
    @Order(6)
    @ColumnWidth(100)
    @DefaultValue("45")
    @TextField("Longitud")
    private Integer columnSize;
    
    @Order(7)
    @ColumnWidth(100)
    @DefaultValue("200")
    @TextField("Ancho")
    private Integer width;
    
    @Order(8)
    @ColumnWidth(100)
    @DefaultValue("1")
    @TextField("Orden")
    private Integer columnOrder;
    
    @Order(9)
    @TextField("Valor por defecto")
    private String defaultValue;
    
    @Order(10)
    @TextField("Opciones")
    private String options;
    
    @Order(11)
    @NotNull
    @TextField("Tabla Lead")
    private LeadTableDto leadTable;

    public TableColumnDto() {
    }

    public TableColumnDto(Integer id) {
        this.id = id;
    }

    public TableColumnDto(Integer id, String name, String columnAlias, String dataType) {
        this.id = id;
        this.name = name;
        this.columnAlias = columnAlias;
        this.dataType = dataType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public LeadTableDto getLeadTable() {
        return leadTable;
    }

    public void setLeadTable(LeadTableDto leadTable) {
        this.leadTable = leadTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableColumnDto)) {
            return false;
        }
        TableColumnDto other = (TableColumnDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacv.marketplatform.entities.TableColumnDto[ id=" + id + " ]";
    }
    
}
