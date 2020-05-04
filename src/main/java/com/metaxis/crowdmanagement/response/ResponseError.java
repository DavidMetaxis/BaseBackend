/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author David Anstee
 * Date Created: 12/03/2018
 * 
 * Populate with a single error that has occurred on the server during a process
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseError extends RestResponse {
    private int code = 0;
    private String error = "";
    private String header = "";

    public ResponseError() {
    }
    
    public ResponseError(int code, String error, String header)
    {
        this.code = code;
        this.error = error;
        this.header = header;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
        
}
