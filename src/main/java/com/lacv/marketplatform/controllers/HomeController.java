package com.lacv.marketplatform.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("redirect:/vista/user/table.htm");

        return mav;
    }
    
    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String redirect) {
        if(redirect!=null){
            try {
                response.sendRedirect(new String(Base64.decodeBase64(redirect), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }else{
            return new ModelAndView("redirect:/vista/user/table.htm");
        }
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/denied", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getDenied() {
        ModelAndView mav = new ModelAndView("denied");
        return mav;
    }
    
}
