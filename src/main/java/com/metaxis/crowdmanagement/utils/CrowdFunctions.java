/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.metaxis.crowdmanagement.response.RestResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAnstee
 */
public class CrowdFunctions {
    
     // get the CSS config file
    public static Properties getPropertyFile()
    {
        Properties properties = new Properties();
        InputStreamReader in = null;
        try {            
            in = new InputStreamReader(new FileInputStream(CrowdProps.CONFIG_FILE_PATH), "UTF-8");
            properties.load(in);
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ex) {}
            }
        }
        return properties;
    }  
    
    /**
     * Convert a POJO of type RestResponse into a string value
     * 
     * @param obj           The RestResponse object
     * @return              A string value of the RestResponse POJO
     */
    public static String getPojoAsString(RestResponse obj) throws JsonProcessingException
    {                        
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer;        
        SimpleFilterProvider provider = new SimpleFilterProvider();
        provider.setFailOnUnknownId(false);
        mapper.setFilters(provider);

        writer=mapper.writer();
        return writer.writeValueAsString(obj);
    } 
    
}
