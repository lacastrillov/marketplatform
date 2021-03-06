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
                .loginPage("/account/login")
                .failureUrl("/account/login?login_error=true")
                .loginProcessingUrl("/account/authenticate").usernameParameter("j_username").passwordParameter("j_password")
                .defaultSuccessUrl("/account/home?redirect=user", false)
                .permitAll();

        http.logout()
                .logoutUrl("/security_logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll();

        http.exceptionHandling().accessDeniedPage("/account/denied");
        
        http.csrf().disable();
        
        http.addFilterAfter(customSecurityFilter, UsernamePasswordAuthenticationFilter.class);

        /***************************
         *    Fixed Permissions
         ***************************/
        http.authorizeRequests().antMatchers("/account/home*").access("isAuthenticated()");
        
    }

}
