/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.services.security.impl;

import com.dot.gcpbasedot.dto.MenuItem;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.UserDetailsDto;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.util.AESEncrypt;
import com.lacv.marketplatform.entities.RoleAuthorization;
import com.lacv.marketplatform.entities.WebResource;
import com.lacv.marketplatform.entities.WebresourceAuthorization;
import com.lacv.marketplatform.entities.WebresourceRole;
import com.lacv.marketplatform.services.RoleAuthorizationService;
import com.lacv.marketplatform.services.WebResourceService;
import com.lacv.marketplatform.services.WebresourceAuthorizationService;
import com.lacv.marketplatform.services.WebresourceRoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lacastrillov
 */
@Service("securityService")
public class SecurityServiceImpl implements AuthenticationProvider, SecurityService, UserDetailsService {
    
    protected static final Logger LOGGER = Logger.getLogger(SecurityServiceImpl.class);
    
    AESEncrypt myInstance= AESEncrypt.getDefault(WebConstants.SECURITY_SALT);

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    RoleAuthorizationService roleAuthorizationService;
    
    @Autowired
    WebResourceService webResourceService;
    
    @Autowired
    WebresourceAuthorizationService webResourceAuthorizationService;
    
    @Autowired
    WebresourceRoleService webResourceRoleService;
    
    @Autowired
    HttpSession session;
    
    private List<WebResource> generalWebResources;
    
    private boolean webResourcesUpdated= false;
    
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        User user = getUser(a.getName());

        if (user != null){
            String contrasena= myInstance.decrypt(user.getPassword(), WebConstants.SECURITY_SEED_PASSW);
            
            if (contrasena.equals(a.getCredentials())) {
                UserDetailsDto userDetails = entityToUserDetail(user);
                if (userDetails.isEnabled() == false) {
                    throw new BadCredentialsException("Error, el usuario esta inactivo");
                } else if (userDetails.isAccountNonLocked() == false) {
                    throw new BadCredentialsException("Error, la cuenta de usuario esta bloqueada");
                }

                Authentication autentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                user.setFailedAttempts(0);
                user.setLastLogin(new Date());
                userService.update(user);
                return autentication;
            } else {
                user.setFailedAttempts(user.getFailedAttempts() + 1);
                userService.update(user);
            }
        }
        throw new BadCredentialsException("Usuario y/o contraseña incorrectos");
    }

    @Override
    public void connect(User user) {
        UserDetailsDto userDetails = entityToUserDetail(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<UserRole> userRoles = userRoleService.findByParameter("user", user);
        for (UserRole usuerRoleList : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuerRoleList.getRole().getName()));
            List<RoleAuthorization> roleAuthorizationList= roleAuthorizationService.findByParameter("role", usuerRoleList.getRole());
            for(RoleAuthorization roleAuthorization: roleAuthorizationList){
                if(authorities.contains(new SimpleGrantedAuthority("OP_"+roleAuthorization.getAuthorization().getName()))==false){
                    authorities.add(new SimpleGrantedAuthority("OP_"+roleAuthorization.getAuthorization().getName()));
                }
            }
        }
        
        return authorities;
    }
    
    public UserDetailsDto getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            try{
                UserDetailsDto userDetails = (UserDetailsDto) authentication.getPrincipal();
                return userDetails;
            }catch(Exception e){
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        UserDetailsDto userDetails = getUserDetails();
        if(userDetails!=null){
            User user = getUser(userDetails.getUsername());
            return user;
        }
        return null;
    }

    private UserDetailsDto entityToUserDetail(User user) {
        UserDetailsDto userDetails = new UserDetailsDto();
        long idUsuariolong = user.getId();
        int idUsuarioInt = (int) idUsuariolong;
        userDetails.setId(idUsuarioInt);
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setNombre(user.getName());
        userDetails.setImgPerfil(user.getUrlPhoto());
        
        validateUserDetails(userDetails, user);

        userDetails.setAuthorities(getGrantedAuthorities(user));

        return userDetails;
    }

    private void validateUserDetails(UserDetailsDto userDetails, User user) {
        if (user.getStatus().equals("Locked")) {
            userDetails.setAccountNonLocked(false);
        } else {
            userDetails.setAccountNonLocked(true);
        }

        if (user.getStatus().equals("Active")) {
            userDetails.setEnabled(true);
        } else {
            userDetails.setEnabled(false);
        }

        userDetails.setCredentialsNonExpired(true);
        userDetails.setAccountNonExpired(true);
    }

    private User getUser(String username) {
        User user = userService.findUniqueByParameter("username", username);

        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = getUser(userName);
        if (user != null){
            UserDetailsDto userDetails = entityToUserDetail(user);
            
            return userDetails;
        }
        return null;
    }
    
    @Override
    public boolean checkAccessResource(String requestURI) {
        UserDetailsDto userDetails= getUserDetails();
        
        //Check Specific resources
        WebResource matchWebResource= webResourceService.findUniqueByParameter("path", requestURI);
        if(matchWebResource==null){
            if(generalWebResources==null || webResourcesUpdated){
                generalWebResources= webResourceService.findByParameter("type", "general");
                webResourcesUpdated= false;
            }
            for(WebResource webResource: generalWebResources){
                Pattern p = Pattern.compile(webResource.getPath());
                Matcher m = p.matcher(requestURI);
                if(m.find()){
                    matchWebResource= webResource;
                    break;
                }
            }
        }
        
        if(matchWebResource!=null && matchWebResource.getIsPublic()==false){
        
            //Verificar si tiene una autorizacion
            List<WebresourceAuthorization> webResourceAuthorizationList= webResourceAuthorizationService.findByParameter("webResource", matchWebResource);
            if(webResourceAuthorizationList.size()>0){
                for(WebresourceAuthorization webresourceAuthorization: webResourceAuthorizationList){
                    if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("OP_"+webresourceAuthorization.getAuthorization().getName()))){
                        return true;
                    }
                }
                return false;
            }else{
                //Verificar si tiene un Rol
                List<WebresourceRole> webResourceRoleList= webResourceRoleService.findByParameter("webResource", matchWebResource);
                if(webResourceRoleList.size()>0){
                    for(WebresourceRole webresourceRole: webResourceRoleList){
                        if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+webresourceRole.getRole().getName()))){
                            return true;
                        }
                    }
                    return false;
                }
            }
            if(userDetails==null){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData) {
        if(session.getAttribute("menuData")==null){
            LOGGER.info("Generate MENU DATA...");
            for (MenuItem itemParent : menuData) {
                itemParent.setVisible(false);
                for(int j=0; j<itemParent.getSubMenus().size(); j++){
                    String requestURI= itemParent.getSubMenus().get(j).getHref();

                    //Check Specific resources
                    boolean visibleMenu= checkAccessResource(requestURI);
                    if(visibleMenu){
                        itemParent.getSubMenus().get(j).setVisible(true);
                        itemParent.setVisible(true);
                    }else{
                        itemParent.getSubMenus().get(j).setVisible(false);
                    }
                }
            }
            session.setAttribute("menuData", menuData);
            return menuData;
        }else{
            LOGGER.info("Get MENU DATA from Session...");
            return (List<MenuItem>)session.getAttribute("menuData");
        }
    }

    public void setWebResourcesUpdated(boolean webResourcesUpdated) {
        this.webResourcesUpdated = webResourcesUpdated;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
    
}
