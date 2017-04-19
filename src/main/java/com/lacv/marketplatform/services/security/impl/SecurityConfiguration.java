package com.lacv.marketplatform.services.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProvider securityService;
    
    @Autowired
    CustomSecurityFilter customSecurityFilter;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?login_error=true")
                .loginProcessingUrl("/authenticate").usernameParameter("j_username").passwordParameter("j_password")
                .permitAll();

        http.logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll();

        http.csrf().disable();

        http.exceptionHandling().accessDeniedPage("/denied");
        
        http.addFilterAfter(customSecurityFilter, UsernamePasswordAuthenticationFilter.class);

        /***************************
         *    Specific Permissions
         ***************************/
        
        // Authorizations
        //http.authorizeRequests().antMatchers("/rest/mail/delete.htm").access("hasAuthority('OP_mail_delete')");
        //http.authorizeRequests().antMatchers("/vista/user/table.htm").access("hasAuthority('OP_user_view')");
        
        // Roles
        //http.authorizeRequests().antMatchers("/vista/user/table.htm").access("hasRole('ROLE_Empleado')");
        
        /***************************
         *    General Permissions
         ***************************/
        
        // Authorizations
//        http.authorizeRequests().antMatchers("/rest/*/find.htm").access("hasAuthority('OP_find')");
//        http.authorizeRequests().antMatchers("/rest/*/create.htm").access("hasAuthority('OP_create')");
//        http.authorizeRequests().antMatchers("/rest/*/update.htm").access("hasAuthority('OP_update')");
//        http.authorizeRequests().antMatchers("/rest/*/delete.htm").access("hasAuthority('OP_delete')");
//        http.authorizeRequests().antMatchers("/rest/*/doProcess.htm","/rest/*/doProcess/*.htm").access("hasAuthority('OP_doProcess')");
        
        // Roles
        http.authorizeRequests().antMatchers("/home**").authenticated();
//        http.authorizeRequests().antMatchers("/vista/*/table.htm", "/vista/*/report/*").access("hasRole('ROLE_Administrator')");
//        http.authorizeRequests().antMatchers("/rest/*/create.htm").access("hasAuthority('OP_create')");
//        http.authorizeRequests().antMatchers("/rest/*/update.htm", "/rest/*/update/byfilter.htm").access("hasAuthority('OP_update')");
//        http.authorizeRequests().antMatchers("/rest/*/delete.htm", "/delete/byfilter.htm").access("hasAuthority('OP_delete')");
//        http.authorizeRequests().antMatchers("/rest/*/doProcess.htm","/rest/*/doProcess/*.htm").access("hasAuthority('OP_doProcess')");
//        
        
    }

}
