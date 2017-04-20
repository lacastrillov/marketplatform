package com.lacv.marketplatform.services.security.impl;

import com.lacv.marketplatform.dtos.UserDetailsDto;
import com.lacv.marketplatform.entities.WebResource;
import com.lacv.marketplatform.entities.WebresourceAuthorization;
import com.lacv.marketplatform.entities.WebresourceRole;
import com.lacv.marketplatform.services.WebResourceService;
import com.lacv.marketplatform.services.WebresourceAuthorizationService;
import com.lacv.marketplatform.services.WebresourceRoleService;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    WebResourceService webResourceService;
    
    @Autowired
    WebresourceAuthorizationService webResourceAuthorizationService;
    
    @Autowired
    WebresourceRoleService webResourceRoleService;

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("x-frame-options", "allow");
        try {
            String requestURI= req.getRequestURI();
            logger.info("CustomTimeoutRedirectFilter INGRESA :"+requestURI);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsDto userDetails= null;
            if(authentication!=null){
                try{
                    userDetails = (UserDetailsDto) authentication.getPrincipal();
                    logger.info("LOGIN ON: "+userDetails.getUsername());
                }catch(Exception e){
                    logger.info("LOGIN ON: "+authentication.getPrincipal());
                }
            }else{
                logger.info("LOGIN OFF");
            }
            
            boolean continueAccess= checkAccessResource(requestURI, userDetails, req, resp);
            
            if(continueAccess){
                chain.doFilter(request, response);
            }
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
                logger.error("AuthenticationException ase ", ase);
                throw ase;
            }

        }
    }
    
    private boolean checkAccessResource(String requestURI, UserDetailsDto userDetails, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        
        //Check Specific resources
        WebResource matchWebResource= webResourceService.findUniqueByParameter("path", requestURI);
        if(matchWebResource==null){
            List<WebResource> generalWebResources= webResourceService.findByParameter("type", "general");
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
                return accessDenied(req, resp, userDetails);
            }else{
                //Verificar si tiene un Rol
                List<WebresourceRole> webResourceRoleList= webResourceRoleService.findByParameter("webResource", matchWebResource);
                if(webResourceRoleList.size()>0){
                    for(WebresourceRole webresourceRole: webResourceRoleList){
                        if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+webresourceRole.getRole().getName()))){
                            return true;
                        }
                    }
                    return accessDenied(req, resp, userDetails);
                }
            }
            if(userDetails==null){
                return accessDenied(req, resp, userDetails);
            }
        }
        
        return true;
    }

    private boolean accessDenied(HttpServletRequest req, HttpServletResponse resp, UserDetailsDto userDetails) throws IOException {
        String ajaxHeader = req.getHeader("X-Requested-With");

        if ("XMLHttpRequest".equals(ajaxHeader)) {
            resp.sendError(403, "Acceso denegado");
        } else if(userDetails!=null) {
            resp.sendRedirect("/denied");
        } else {
            String redirectUrl= req.getRequestURI() + ((req.getQueryString()!=null)?"?"+req.getQueryString():"");
            resp.sendRedirect("/home?redirect="+redirectUrl);
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
