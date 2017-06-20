package com.lacv.marketplatform.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tempprocess")
public class TempProcessController {
    
    
    @ResponseBody
    @RequestMapping(value = "/ajax/inbody", method = {RequestMethod.POST})
    public byte[] inBody(HttpServletRequest request, HttpServletResponse response) {
        String jsonBody;
        try {
            jsonBody = IOUtils.toString(request.getInputStream());
            JSONObject jsonObject= new JSONObject(jsonBody);
            return getStringBytes(jsonObject.toString());
        } catch (IOException ex) {
            return getStringBytes("ERROR inBody");
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/ajax/inparameters", method = {RequestMethod.POST})
    public byte[] inParameters(HttpServletRequest request, HttpServletResponse response) {
        String jsonIn;
        Map<String, String[]> map= request.getParameterMap();
        JSONObject jsonObject= new JSONObject(map);
        jsonIn= jsonObject.toString();
        
        return getStringBytes(jsonIn);
    }
    
    protected byte[] getStringBytes(String data){
        try {
            return data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
}
