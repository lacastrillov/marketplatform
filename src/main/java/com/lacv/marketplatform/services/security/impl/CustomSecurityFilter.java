package com.lacv.marketplatform.services.security.impl;

import com.lacv.marketplatform.dtos.UserDetailsDto;
import com.lacv.marketplatform.services.security.SecurityService;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.NestedServletException;

/**
 * Clase que valida las peticiones Ajax, debe ser tenido en cuenta desde los js
 * Retorna 901, cuando no se tiene sesion, para que se utilice este coódi en el
 * ajax en caso de un redirect
 *
 * @author Harley Aranda Insoftar.
 * @copy Todos los derechos reservados Metrocuadrado.
 * @version 1.0
 */
@Component
public class CustomSecurityFilter extends GenericFilterBean {
    
    @Autowired
    SecurityService securityService;

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("x-frame-options", "allow");
        try {
            String requestURI= req.getRequestURI();
            logger.info("CustomSecurityFilter INGRESA :"+requestURI);
            
            boolean continueAccess= securityService.checkAccessResource(requestURI);
            
            if(continueAccess){
                chain.doFilter(request, response);
            }else{
                accessDenied(req, resp);
            }
            logger.info("CustomSecurityFilter FIN ");
        } catch (Exception ex) {
            logger.error("CustomSecurityFilter ex ", ex);
            
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);

            if (ase == null) {
                ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
            }
            if (ase != null) {
                logger.error("AuthenticationException ase ", ase);
                throw ase;
            }
        }
    }

    private boolean accessDenied(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDetailsDto userDetails= securityService.getUserDetails();
        String ajaxHeader = req.getHeader("X-Requested-With");

        if ("XMLHttpRequest".equals(ajaxHeader)) {
            resp.sendError(403, "Acceso denegado");
        } else if(userDetails!=null) {
            resp.sendRedirect("/denied");
        } else {
            String queryString= "";
            if(req.getQueryString()!=null){
                queryString= "?" + URLDecoder.decode(req.getQueryString(), "UTF-8");
            }
            String redirectUrl= req.getRequestURI() + queryString;
            resp.sendRedirect("/home?redirect="+Base64.encodeBase64String(redirectUrl.getBytes("UTF-8")));
        }
        
        return false;
    }

    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {

        @Override
        protected void initExtractorMap() {
            super.initExtractorMap();
            registerExtractor(ServletException.class, new ThrowableCauseExtractor() {

                @Override
                public Throwable extractCause(Throwable throwable) {
                    verifyThrowableHierarchy(throwable, NestedServletException.class);
                    return ((NestedServletException) throwable).getRootCause();
                }
                
            });
        }
        
    }
    
}
