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
public class Constants {

    public Constants() {
    }
    public static String API_BASE = "app";
    public static String DATA_SAVED = "DATA_SAVED_SUCCESSFULLY";
    public static String DATA_SAVE_FAILED = "DATA_VALIDATION_FAILED";
    public static String DATE_FORMAT = "dd/MM/yyyy";
    public static String DATA_NOT_AVAILABLE = "NA";
    public static String USER_NOT_FOUND = "USER_NOT_FOUND";

    public enum MasterAssessor {
        ADMIN, PROJECT, AUDITOR, CONSULTANT, PENTESTER, DEVELOPER;
    }

    public enum RISK_FACTOR {
        LOW, MEDIUM, HIGH
    }

    public enum TicketStatus {
        NEW, DEVELOPER_PROGRESS, DEVELOPER_PENDING, ASSESSOR_PROGRESS, ASSESSOR_PENDING, HOLD, SOLVED, CLOSED
    }
}
