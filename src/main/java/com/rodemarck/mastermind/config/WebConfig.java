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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author RODEMARCK.MELOJ
 */

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
    private static final String[] ARTEFATOS  =  {
            "/css/**",
            "/img/**",
            "/fonts/**",
            "/js/**",
            "/components/**",
            "/templates/**",
            "/resources/**",
            "/webjar/**"
    };

    @Autowired
    private Detalhes detalhes;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
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
                    .and()
                    .logout()
                        .logoutUrl("/deslogar")
                            .permitAll()
                    /*.and()
                    .csrf()
                        .disable()*/;
    }
}

