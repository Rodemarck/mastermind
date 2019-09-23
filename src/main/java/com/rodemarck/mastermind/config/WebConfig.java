/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.config;

import com.rodemarck.mastermind.model.user.Detalhes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * @author RODEMARCK.MELOJ
 */

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
    private static final String[] ARTEFATOS = {
            "/css/**",
            "/img/**",
            "/fonts/**",
            "/js/**",
            "/components/**",
            "/templates/**",
            "/resources/**"
    };

    @Autowired
    private Detalhes detalhes;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("socorro!!!"+ auth.toString());

        auth.userDetailsService(detalhes).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers(ARTEFATOS)
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
            .formLogin()
                .loginPage("/logar")
                    .permitAll()
                        .successHandler(authenticationSuccessHandler)
            .and()
            .logout()
                .logoutUrl("/deslogar")
                    .permitAll()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and()
            .sessionManagement()
                .invalidSessionUrl("/logar")

                    /*.csrf()
                        .disable()*/;
    }
}

