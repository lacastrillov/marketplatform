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
                .logoutSuccessUrl("/home.htm")
                .invalidateHttpSession(true)
                .permitAll();

        http.csrf().disable();

        http.exceptionHandling().accessDeniedPage("/denied");
        
        http.addFilterAfter(customSecurityFilter, UsernamePasswordAuthenticationFilter.class);

        /***************************
         *    Fixed Authorizations
         ***************************/
        http.authorizeRequests().antMatchers("/home.htm").authenticated();
        
    }

}
