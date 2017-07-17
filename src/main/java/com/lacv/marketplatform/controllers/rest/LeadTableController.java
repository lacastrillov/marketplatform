/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.LeadTableMapper;
import com.lacv.marketplatform.services.LeadTableService;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.dto.TableColumnDB;
import com.dot.gcpbasedot.service.JdbcDirectService;
import com.dot.gcpbasedot.service.gcp.StorageService;
import com.lacv.marketplatform.entities.LeadTable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping(value="/rest/leadTable")
public class LeadTableController extends RestController {
    
    @Autowired
    LeadTableService leadTableService;
    
    @Autowired
    JdbcDirectService jdbcDirectService;
    
    @Autowired
    LeadTableMapper leadTableMapper;
    
    @Autowired
    StorageService storageService;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("leadTable", leadTableService, leadTableMapper);
    }
    
    @RequestMapping(value = "/create.htm", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public byte[] create(@RequestParam(required= false) String data, HttpServletRequest request) {
        byte[] result= super.create(data, request);
        
        try{
            JSONObject jsonResult= new JSONObject(new String(result, StandardCharsets.UTF_8));
            if(jsonResult.getBoolean("success")){
                List<TableColumnDB> columns= new ArrayList<>();
                TableColumnDB idColumn= new TableColumnDB();
                idColumn.setColumnName("id");
                idColumn.setDataType("INT");
                idColumn.setIsAutoIncrement(true);
                idColumn.setIsNotNull(true);
                idColumn.setIsPrimaryKey(true);
                columns.add(idColumn);

                String tableName= getLeadTableName(jsonResult.getJSONObject("data").getString("tableAlias"));

                jdbcDirectService.createTable(tableName, columns);
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
        String oldTableAlias="";
        
        try {
            String jsonData= data;
            if(jsonData==null){
                jsonData = IOUtils.toString(request.getInputStream());
            }
            JSONObject jsonObject= new JSONObject(jsonData);
            
            LeadTable leadTable= leadTableService.findById(Integer.parseInt(jsonObject.get("id").toString()));
            oldTableAlias= leadTable.getTableAlias();
        } catch (Exception e) {
            LOGGER.error("update " + entityRef, e);
        }
        
        byte[] result= super.update(data, request);
        
        try{
            JSONObject jsonResult= new JSONObject(new String(result, StandardCharsets.UTF_8));
            if(jsonResult.getBoolean("success") && !jsonResult.getJSONObject("data").getString("tableAlias").equals(oldTableAlias)){
                String tableName= getLeadTableName(oldTableAlias);
                String newTableName= getLeadTableName(jsonResult.getJSONObject("data").getString("tableAlias"));
                jdbcDirectService.changeTableName(tableName, newTableName);
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
        String result= super.delete(data);
        
        try{
            JSONObject jsonResult= new JSONObject(result);
            if(jsonResult.getBoolean("success")){
                String tableName= getLeadTableName(jsonResult.getJSONObject("data").getString("tableAlias"));

                jdbcDirectService.dropTable(tableName);
            }
        }catch(Exception e){
            LOGGER.error("update " + entityRef, e);
        }
        
        return result;
    }
    
    private String getLeadTableName(String originalName){
        return "lt_"+originalName.toLowerCase().replaceAll(" ", "_");
    }
    
}
