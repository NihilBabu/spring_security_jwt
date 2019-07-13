/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.components;

import com.nihil.securityjwt.ejb.Users;
import com.nihil.securityjwt.repository.UserRoleRepo;
import com.nihil.securityjwt.repository.UsersRepo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nihil
 */
@Service
public class AuthenticationComponent implements UserDetailsService {

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserRoleRepo userRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        List<Users> allUsers = usersRepo.findAllUsersByEmail(string);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (allUsers != null && allUsers.size() > 0) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleRepo.findByUserId(allUsers.get(0).getUserId()).getRoleId().getRole()));
            Users user = allUsers.get(0);
            return new User(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(string + " Not Found in the System");
        }
    }
}
