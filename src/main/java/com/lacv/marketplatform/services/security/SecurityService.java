package com.lacv.marketplatform.services.security;

import com.dot.gcpbasedot.dto.MenuItem;
import com.lacv.marketplatform.dtos.security.UserDetailsDto;
import com.lacv.marketplatform.entities.User;
import java.util.List;
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
    
    boolean connect(String username, String password) throws AuthenticationException;
    
    UserDetailsDto getUserDetails();
    
    User getCurrentUser();
    
    boolean checkAccessResource(String requestURI);
    
    List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData);
    
    void reconfigureAccessControl();

}
