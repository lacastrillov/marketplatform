/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.TableColumnMapper;
import com.lacv.marketplatform.services.TableColumnService;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.dto.TableColumnDB;
import com.dot.gcpbasedot.service.JdbcDirectService;
import com.dot.gcpbasedot.service.gcp.StorageService;
import com.dot.gcpbasedot.util.Formats;
import com.lacv.marketplatform.entities.TableColumn;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/tableColumn")
public class TableColumnController extends RestController {
    
    @Autowired
    TableColumnService tableColumnService;
    
    @Autowired
    JdbcDirectService jdbcDirectService;
    
    @Autowired
    TableColumnMapper tableColumnMapper;
    
    @Autowired
    StorageService storageService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("tableColumn", tableColumnService, tableColumnMapper);
    }
    
    @RequestMapping(value = "/create.htm", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public byte[] create(@RequestParam(required= false) String data, HttpServletRequest request) {
        JSONObject jsonObject=null;
        try {
            String jsonData= data;
            if(jsonData==null){
                jsonData = IOUtils.toString(request.getInputStream());
            }
            jsonObject= new JSONObject(jsonData);
            String columnAlias= formatColumnAlias(jsonObject.getString("columnAlias"));
            jsonObject.put("columnAlias", columnAlias);
        } catch (Exception e) {
            LOGGER.error("update " + entityRef, e);
        }
        
        byte[] result= super.create(jsonObject.toString(), request);
        
        try{
            JSONObject jsonResult= new JSONObject(new String(result, StandardCharsets.UTF_8));
            if(jsonResult.getBoolean("success")){
                JSONObject jsonColumn= jsonResult.getJSONObject("data");
                TableColumn tableColumn= tableColumnService.findById(jsonColumn.getInt("id"));
                String tableName= tableColumn.getLeadTable().getTableAlias();
                
                TableColumnDB column= new TableColumnDB();
                column.setColumnName(jsonColumn.getString("columnAlias"));
                column.setDataType(jsonColumn.getString("dataType"));
                column.setDataTypeDB(Formats.getDatabaseType(jsonColumn.getString("dataType")));
                if(jsonColumn.getString("dataType").equals("java.lang.String")){
                    column.setColumnSize(jsonColumn.getInt("columnSize"));
                }

                jdbcDirectService.addTableColumn(tableName, column);
            }
        }catch(Exception e){
            LOGGER.error("create " + entityRef, e);
        }
        
        return result;
    }

    @RequestMapping(value = "/update.htm", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    @Override
    public byte[] update(@RequestParam(required= false) String data, HttpServletRequest request) {
        String oldColumnAlias="";
        String tableName="";
        JSONObject jsonObject=null;
        try {
            String jsonData= data;
            if(jsonData==null){
                jsonData = IOUtils.toString(request.getInputStream());
            }
            jsonObject= new JSONObject(jsonData);
            if(jsonObject.has("columnAlias")){
                String columnAlias= formatColumnAlias(jsonObject.getString("columnAlias"));
                jsonObject.put("columnAlias", columnAlias);
            }
            TableColumn tableColumn= tableColumnService.findById(jsonObject.getInt("id"));
            oldColumnAlias= tableColumn.getColumnAlias();
            tableName= tableColumn.getLeadTable().getTableAlias();
        } catch (Exception e) {
            LOGGER.error("update " + entityRef, e);
        }
        
        byte[] result= super.update(jsonObject.toString(), request);
        
        try{
            JSONObject jsonResult= new JSONObject(new String(result, StandardCharsets.UTF_8));
            if(jsonResult.getBoolean("success")){
                JSONObject jsonColumn= jsonResult.getJSONObject("data");
                
                TableColumnDB column= new TableColumnDB();
                column.setColumnName(jsonColumn.getString("columnAlias"));
                column.setDataTypeDB(Formats.getDatabaseType(jsonColumn.getString("dataType")));
                if(jsonColumn.getString("dataType").equals("java.lang.String")){
                    column.setColumnSize(jsonColumn.getInt("columnSize"));
                }
                
                jdbcDirectService.changeTableColumn(tableName, oldColumnAlias, column);
            }
        }catch(Exception e){
            LOGGER.error("update " + entityRef, e);
        }
        
        return result;
    }
    
    @RequestMapping(value = "/delete.htm", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseBody
    @Override
    public String delete(@RequestParam String data) {
        String tableName="";
        String columnAlias= "";
        JSONObject jsonObject=null;
        try {
            String jsonData= data;
            jsonObject= new JSONObject(jsonData);
            TableColumn tableColumn= tableColumnService.findById(jsonObject.getInt("id"));
            columnAlias= tableColumn.getColumnAlias();
            tableName= tableColumn.getLeadTable().getTableAlias();
        } catch (Exception e) {
            LOGGER.error("update " + entityRef, e);
        }
        
        String result= super.delete(data);
        
        try{
            JSONObject jsonResult= new JSONObject(result);
            if(jsonResult.getBoolean("success")){
                jdbcDirectService.dropTableColumn(tableName, columnAlias);
            }
        }catch(Exception e){
            LOGGER.error("update " + entityRef, e);
        }
        
        return result;
    }
    
    private String formatColumnAlias(String originalName){
        return Formats.stripAccents(originalName).toLowerCase().replaceAll(" ", "_");
    }
    
}
