/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.services.security.impl;

import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.UserDetailsDto;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.util.AESEncrypt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Autowired
    UserService usuarioService;

    @Autowired
    UserRoleService userRoleService;
    
    AESEncrypt myInstance= AESEncrypt.getDefault(WebConstants.SECURITY_SALT);
    
    private final Integer SESSION_MAX_INTENTOS=6;
    
    private final Integer SESSION_TIEMPO_DIAS_CAMBIO_CLAVE=360;
    
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        User user = getUser(a.getName());

        if (user != null){
            String contrasena= myInstance.decrypt(user.getPassword(), WebConstants.SECURITY_SEED_PASSW);
            
            if (contrasena.equals(a.getCredentials())) {
                UserDetailsDto userDetails = entityToUserDetail(user);
                if (userDetails.isAccountNonExpired() == false) {
                    if (user.getStatus().equals("Inactive")) {
                        throw new BadCredentialsException("El usuario no ha activado la cuenta por correo");
                    } else {
                        throw new BadCredentialsException("El usuario se encuentra en estado " + user.getStatus());
                    }
                } else if (userDetails.isEnabled() == false) {
                    throw new BadCredentialsException("La clave esta vencida");
                } else if (userDetails.isAccountNonLocked() == false) {
                    throw new BadCredentialsException("La cuenta esta bloqueada por que ha excedido los intentos fallidos");
                }

                Authentication autentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                user.setFailedAttempts(0);
                user.setLastLogin(new Date());
                usuarioService.update(user);
                return autentication;
            } else {
                user.setFailedAttempts(user.getFailedAttempts() + 1);
                usuarioService.update(user);
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

        Parameters p = new Parameters();
        p.whereEqual("user", user);
        List<UserRole> userRoles = userRoleService.findByParameters(p);
        for (UserRole usuarioRol : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuarioRol.getRole().getName()));
        }
        authorities.add(new SimpleGrantedAuthority("OP_create"));
        authorities.add(new SimpleGrantedAuthority("OP_update"));
        authorities.add(new SimpleGrantedAuthority("OP_mail_delete"));
        authorities.add(new SimpleGrantedAuthority("OP_user_view"));
        
        return authorities;
    }

    @Override
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            try {
                UserDetailsDto userDetails = (UserDetailsDto) principal;
                User user = getUser(userDetails.getUsername());
                return user;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
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
        if (user.getStatus().equals("Active")) {
            userDetails.setAccountNonExpired(true);
                
            if (user.getFailedAttempts() < SESSION_MAX_INTENTOS) {
                userDetails.setAccountNonLocked(true);
            } else {
                userDetails.setAccountNonLocked(false);
            }

            if (user.getLastLogin()==null || diasEntre(user.getLastLogin(), new Date()) < SESSION_TIEMPO_DIAS_CAMBIO_CLAVE) {
                userDetails.setEnabled(true);
            } else {
                userDetails.setEnabled(false);
            }

        } else if (user.getStatus().equals("Inactive") && user.getLastLogin() == null) {
            userDetails.setCredentialsNonExpired(true);
        } else {
            userDetails.setAccountNonExpired(false);
        }
    }

    private User getUser(String username) {
        User user = null;
        try {
            Parameters p = new Parameters();
            p.whereEqual("username", username);
            user = usuarioService.findUniqueByParameters(p);
        } catch (Exception ex) {
            Logger.getLogger(SecurityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    public boolean supports(Class<?> type) {
        return true;
    }

    @Override
    public String getCurrentUserEmail() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            try {
                UserDetailsDto userDetails = (UserDetailsDto) principal;
                User user = getUser(userDetails.getUsername());
                return user.getEmail();
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }
    
    private long diasEntre(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

}
