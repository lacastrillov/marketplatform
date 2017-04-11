package com.lacv.marketplatform.services.security.impl;

import com.lacv.marketplatform.dtos.UserDetailsDto;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
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
public class CustomSecurityFilter extends GenericFilterBean {

    private static final int ERROR_SESSION = 901;

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            String ruta= req.getRequestURL().toString();
            logger.info("CustomTimeoutRedirectFilter INGRESA :"+ruta);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication!=null){
                try{
                    UserDetailsDto userDetails = (UserDetailsDto) authentication.getPrincipal();
                    logger.info("LOGIN ON: "+userDetails.getUsername());
                }catch(Exception e){
                    logger.info("LOGIN ON: "+authentication.getPrincipal());
                }
            }else{
                logger.info("LOGIN OFF");
            }
            
            if(ruta.contains("/rest/user/find.htm")){
                resp.sendError(403, "Acceso denegado");
            }
            
            chain.doFilter(request, response);
            //resp.sendRedirect("/denied");
            logger.info("CustomTimeoutRedirectFilter FIN ");
        } catch (Exception ex) {
            logger.error("CustomTimeoutRedirectFilter ex ", ex);
            
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);

            if (ase == null) {
                ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
            }

            if (ase != null) {

                if (ase instanceof AuthenticationException) {
                    logger.error("AuthenticationException ase ", ase);
                    throw ase;
                } else if (ase instanceof AccessDeniedException) {
                    logger.error("AccessDeniedException ase ", ase);
                    accessDenied(request, resp, ase);
                }
            }

        }
    }

    private void accessDenied(ServletRequest request, HttpServletResponse resp, RuntimeException ase) throws IOException {
        if (authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
            String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");

            if ("XMLHttpRequest".equals(ajaxHeader)) {
                resp.sendError(ERROR_SESSION);
            } else {
                throw ase;
            }
        } else {
            throw ase;
        }
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
