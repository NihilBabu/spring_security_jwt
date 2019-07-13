/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.controller;

import com.nihil.securityjwt.components.GenericComponent;
import com.nihil.securityjwt.hawk.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nihil
 */
@RestController
@RequestMapping("/rest/hello")
public class HelloController {

    @Autowired
    GenericComponent genericComponent;

    @GetMapping
    public String hello() {
        if (genericComponent.getAuthentication(Constants.MasterAssessor.ADMIN)) {
            System.out.println("ADMIN");
        }
        return "Hello World";
    }

}
