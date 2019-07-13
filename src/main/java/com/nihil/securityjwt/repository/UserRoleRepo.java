/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.repository;

import com.nihil.securityjwt.ejb.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nihil
 */

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

    @Query("SELECT u FROM UserRole u WHERE u.userId = :userId")
    public UserRole findByUserId(@Param("userId") Integer userId);

}
