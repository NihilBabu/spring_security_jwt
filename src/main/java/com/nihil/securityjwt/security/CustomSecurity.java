/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Nihil
 */
@Configuration
public class CustomSecurity {

    @Bean
    public PlainTextPasswordEncoder getPlainTextPasswordEncoder() {
        return new PlainTextPasswordEncoder();
    }
}
