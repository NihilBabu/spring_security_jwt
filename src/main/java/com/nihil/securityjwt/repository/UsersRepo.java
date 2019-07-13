/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.repository;

import com.nihil.securityjwt.ejb.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nihil
 */
public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    public List<Users> findAllUsersByEmail(@Param("email")String string);
    
    
}
