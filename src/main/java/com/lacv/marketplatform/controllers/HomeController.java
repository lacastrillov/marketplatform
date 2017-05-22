package com.lacv.marketplatform.controllers;

import com.dot.gcpbasedot.dao.Parameters;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    UserRoleService userRoleService;
    
    
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("redirect:/tienda/");

        return mav;
    }
    
    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) String redirect) {
        if(!redirect.equals("user")){
            try {
                response.sendRedirect(new String(Base64.decodeBase64(redirect), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }else{
            User user= securityService.getCurrentUser();
            if(user!=null){
                Parameters p= new Parameters();
                p.whereEqual("user", user);
                p.orderBy("role.priorityCheck", "ASC");
                List<UserRole> userRoles= userRoleService.findByParameters(p);
                String homePage=null;
                for(UserRole userRole: userRoles){
                    if(userRole.getRole().getHomePage()!=null){
                        homePage= userRole.getRole().getHomePage();
                        break;
                    }
                }
                if(homePage!=null){
                    return new ModelAndView("redirect:"+homePage);
                }else{
                    return new ModelAndView("redirect:/vista/product/table.htm");
                }
            }else{
                return new ModelAndView("redirect:/login");
            }
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
