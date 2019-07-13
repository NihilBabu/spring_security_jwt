/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.components;

import com.nihil.securityjwt.hawk.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nihil
 */
@Component
public class GenericComponent {

    public String getCurrentUser() {
        return (String) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

    }
    
    public Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().
                getAuthentication();

    }
    
    public boolean getAuthentication(Constants.MasterAssessor assessor) {
        return getCurrentAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_" + assessor.name()));

    }
    
}
