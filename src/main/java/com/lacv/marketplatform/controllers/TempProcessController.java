package com.lacv.marketplatform.controllers;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

@Controller
@RequestMapping(value = "/tempprocess")
public class TempProcessController {
    
    
    @RequestMapping(value = "/inbody", method = {RequestMethod.POST})
    public String inBody(HttpServletRequest request, HttpServletResponse response) {
        String jsonBody;
        try {
            jsonBody = IOUtils.toString(request.getInputStream());
            JSONObject jsonObject= new JSONObject(jsonBody);
            return jsonObject.toString();
        } catch (IOException ex) {
            return "ERROR inBody";
        }
    }
    
    @RequestMapping(value = "/inparameters", method = {RequestMethod.POST})
    public String inParameters(HttpServletRequest request, HttpServletResponse response) {
        String jsonIn;
        Map<String, String[]> map= request.getParameterMap();
        JSONObject jsonObject= new JSONObject(map);
        jsonIn= jsonObject.toString();
        
        return jsonIn;
    }
    
}
