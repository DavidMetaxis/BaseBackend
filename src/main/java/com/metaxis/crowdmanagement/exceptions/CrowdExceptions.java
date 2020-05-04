/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 *
 * @author DAnstee
 */
public class CrowdExceptions {
    
    public static String getException(Exception ex, String target)
    {
        try {
            ResourceBundle mybundle = ResourceBundle.getBundle("Exceptions");
            return mybundle.getString(target);
        } catch(Exception e) {
            return target;
        }
    }
    
    public static String getException(String target)
    {
        try {            
            ResourceBundle mybundle = ResourceBundle.getBundle("Exceptions");
            return mybundle.getString(target);
        } catch(Exception e) {
            return target;
        }
    }
    
    public static String getException(String target, Object[] parameters)
    {        
        try {
            ResourceBundle mybundle = ResourceBundle.getBundle("Exceptions");
            MessageFormat formatter = new MessageFormat("");

            formatter.applyPattern(mybundle.getString(target));
            String output = formatter.format(parameters);

            return output;
        } catch(Exception e) {
            return target;
        }
    }
    
}
