/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.services.security.impl;

import com.dot.gcpbasedot.dto.MenuItem;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.security.UserDetailsDto;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.util.AESEncrypt;
import com.lacv.marketplatform.dtos.security.WebResourceAuthorities;
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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
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
    
    private Map<String, WebResourceAuthorities> specificWebResources;
    
    private Map<String, WebResourceAuthorities> generalWebResources;
    
    
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
    
    @Override
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
        WebResourceAuthorities webResourceAuthorities= specificWebResources.get(requestURI);
        if(webResourceAuthorities==null){
            for(Map.Entry<String, WebResourceAuthorities> entry : generalWebResources.entrySet()){
                try{
                    Pattern p = Pattern.compile(entry.getKey());
                    Matcher m = p.matcher(requestURI);
                    if(m.find()){
                        webResourceAuthorities= entry.getValue();
                        break;
                    }
                }catch(Exception e){
                    LOGGER.error("ERROR Pattern - Matcher...", e);
                }
            }
        }
        
        if(webResourceAuthorities!=null && webResourceAuthorities.getIsPublic()==false){
        
            //Verificar si tiene una autorizacion
            if(webResourceAuthorities.getAuthorizations().size()>0){
                for(String authorization : webResourceAuthorities.getAuthorizations()){
                    if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("OP_"+authorization))){
                        return true;
                    }
                }
                return false;
            }else{
                //Verificar si tiene un Rol
                if(webResourceAuthorities.getRoles().size()>0){
                    for(String role: webResourceAuthorities.getRoles()){
                        if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+role))){
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
        for (MenuItem itemParent : menuData) {
            itemParent.setVisible(false);
            for(int j=0; j<itemParent.getSubMenus().size(); j++){
                String requestURI= itemParent.getSubMenus().get(j).getHref();
                boolean visibleMenu= checkAccessResource(requestURI);
                if(visibleMenu){
                    itemParent.getSubMenus().get(j).setVisible(true);
                    itemParent.setVisible(true);
                }else{
                    itemParent.getSubMenus().get(j).setVisible(false);
                }
            }
        }
        return menuData;
    }

    @Override
    @PostConstruct
    public void reconfigureAccessControl(){
        LOGGER.info("Reconfigure Access Control...");
        specificWebResources= new HashMap<>();
        generalWebResources= new HashMap<>();
        List<WebResource> webResources= webResourceService.listAll();
        
        for(WebResource webResource: webResources){
            List<WebresourceAuthorization> webResourceAuthorizationList= webResourceAuthorizationService.findByParameter("webResource", webResource);
            List<WebresourceRole> webResourceRoleList= webResourceRoleService.findByParameter("webResource", webResource);
            
            WebResourceAuthorities webResourceAuthorities= new WebResourceAuthorities(webResource.getPath());
            webResourceAuthorities.setIsPublic(webResource.getIsPublic());
            for(WebresourceAuthorization webresourceAuthorization: webResourceAuthorizationList){
                webResourceAuthorities.addAuthorization(webresourceAuthorization.getAuthorization().getName());
            }
            for(WebresourceRole webresourceRole: webResourceRoleList){
                webResourceAuthorities.addRole(webresourceRole.getRole().getName());
            }
            
            if(webResource.getType().equals("specific")){
                specificWebResources.put(webResource.getPath(), webResourceAuthorities);
            }else{
                generalWebResources.put(webResource.getPath(), webResourceAuthorities);
            }
        }
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
    
}
