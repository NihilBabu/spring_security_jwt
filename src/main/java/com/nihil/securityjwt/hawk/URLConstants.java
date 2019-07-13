/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.hawk;

/**
 *
 * @author Nihil
 */
public class URLConstants {

    public URLConstants() {
    }
    public static final String USERS = "/users";
    public static final String USERS_MANAGE_ROLE = USERS + "/manage-role";

    public static final String VULNERABILITY = "/vulnerability";
    public static final String VULNERABILITY_REPORT = VULNERABILITY + "/report";
    public static final String VULNERABILITY_TIMELINE = VULNERABILITY + "/timeline";
    public static final String VULNERABILITY_MASTER = VULNERABILITY + "/master";
}
