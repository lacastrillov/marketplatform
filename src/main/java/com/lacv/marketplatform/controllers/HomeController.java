package com.lacv.marketplatform.controllers;

import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.util.Util;
import com.lacv.marketplatform.dtos.RoleDto;
import com.lacv.marketplatform.dtos.UserDto;
import com.lacv.marketplatform.dtos.UserRoleDto;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.mappers.UserMapper;
import com.lacv.marketplatform.mappers.UserRoleMapper;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    UserRoleMapper userRoleMapper;
    
    
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
                } else {
                    return new ModelAndView("redirect:/vista/product/entity.htm");
                }
            }else{
                return new ModelAndView("redirect:/login");
            }
        }
    }
    
    @RequestMapping(value = "/ajax/authenticate", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String authenticate(@RequestParam(required = true) String j_username, @RequestParam(required = true) String j_password) {
        Map data= new HashMap();
        try{
            securityService.connect(j_username, j_password);
            User user= securityService.getCurrentUser();
            UserDto userDto= (UserDto) userMapper.entityToDto(user);
            List<UserRole> userRoles= userRoleService.findByParameter("user", user);
            List<UserRoleDto> userRolesDto= (List<UserRoleDto>) userRoleMapper.listEntitiesToListDtos(userRoles);
            List<RoleDto> roles= new ArrayList<>();
            for(UserRoleDto userRole: userRolesDto){
                roles.add(userRole.getRole());
            }
            data.put("success", true);
            data.put("user", userDto);
            data.put("roles", roles);
            
            return Util.objectToJson(data);
        }catch(AuthenticationException ex){
            data.put("success", false);
            data.put("message", "Usuario y/o contraseña incorrectos");
        }
        return Util.objectToJson(data);
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
        User user= securityService.getCurrentUser();
        if(user!=null){
            return getHome(request, response, "user");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/denied", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getDenied() {
        ModelAndView mav = new ModelAndView("denied");
        return mav;
    }
    
}
