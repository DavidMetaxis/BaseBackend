/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.exceptions;

/**
 *  Exception codes for any errors that occur in Crowd
 * 
 * @author David Anstee
 * @since 21/06/2018
 * @version 1.0
 * 
 * ----UPDATES----
 */
public class CrowdExceptionCodes {
    
     // authentication exceptions
    public static final int USER_AUTHENTICATION_CODE_ERROR = 101;
    public static final int ARCHIE_AUTHENTICATION_ERROR = 102;
    public static final int TEST_USER_AUTHENTICATION_ERROR = 103;  
    public static final int THIS_AUTHENTICATION_ERROR = 104;
    public static final int USER_AUTHENTICATION_ERROR = 105;
    public static final int USER_AUTHENTICATION_TOKEN_ERROR = 106;
    public static final int THIS_USER_AUTHENTICATION_ERROR = 107;
    public static final int THIS_USER_DETAILS_ERROR = 108;
    public static final int SSO_AUTHENTICATION_ERROR = 109;
    public static final int SSO_AUTHENTICATION_TOKEN_ERROR = 110;
    public static final int SSO_USER_AUTHENTICATION_ERROR = 111;
    public static final int SSO_USER_DETAILS_ERROR = 112;
    
    
    
    // training record errors
    public static final int EXAMPLE_TRAINING_RECORD_ERROR = 2001;
}
