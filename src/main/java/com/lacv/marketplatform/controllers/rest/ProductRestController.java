/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest;


import com.lacv.marketplatform.mappers.ProductMapper;
import com.lacv.marketplatform.services.ProductService;
import com.dot.gcpbasedot.controller.RestSessionController;
import com.dot.gcpbasedot.domain.BaseEntity;
import javax.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/product")
public class ProductRestController extends RestSessionController {
    
    @Autowired
    ProductService productService;
    
    @Autowired
    ProductMapper productMapper;
    
    
    @PostConstruct
    public void init(){
        super.addControlMapping("product", productService, productMapper);
    }

    @Override
    public JSONObject addSessionSearchFilter(JSONObject jsonFilters) {
        return jsonFilters;
    }

    @Override
    public JSONObject addSessionReportFilter(String reportName, JSONObject jsonFilters) {
        return jsonFilters;
    }

    @Override
    public boolean canLoad(BaseEntity entity) {
        return true;
    }

    @Override
    public boolean canCreate(BaseEntity entity) {
        return false;
    }

    @Override
    public boolean canUpdate(BaseEntity entity) {
        return false;
    }

    @Override
    public boolean canDelete(BaseEntity entity) {
        return false;
    }

    @Override
    public boolean canUpdateByFilters(JSONObject jsonFilters) {
        return false;
    }

    @Override
    public boolean canDeleteByFilters(JSONObject jsonFilters) {
        return false;
    }
    
}
