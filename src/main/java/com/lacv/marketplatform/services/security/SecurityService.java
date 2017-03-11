package com.lacv.marketplatform.services.security;

import com.lacv.marketplatform.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;





/**
 *
 * Interfaz que expone los servicios de seguridad
 *
 * @author Harley Aranda / Edison Neira Todos los derechos reservados
 * @Version 1.0
 */
public interface SecurityService {

    Authentication authenticate(Authentication a) throws AuthenticationException;
    
    void connect(User user);
    
    public User getCurrentUser();
    
    public String getCurrentUserEmail();

}
