/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.tasktraining.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaxis.crowdmanagement.response.RestResponse;


/**
 * Contains the content of a training record 
 * 
 * @author David Anstee 
 * @since 01/05/2020
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TrainingRecord extends RestResponse {
    private Long recordid = 0L;
    private int taskid = 0;
    private String recordtype = "";    
    private String pagehtml = "";
    private String responseshtml = "";    
    private String responsehighlights = "";

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }
   
    public String getPagehtml() {
        return pagehtml;
    }

    public void setPagehtml(String pagehtml) {
        this.pagehtml = pagehtml;
    }

    public String getResponseshtml() {
        return responseshtml;
    }

    public void setResponseshtml(String responseshtml) {
        this.responseshtml = responseshtml;
    }

    public String getResponsehighlights() {
        return responsehighlights;
    }

    public void setResponsehighlights(String responsehighlights) {
        this.responsehighlights = responsehighlights;
    }
    
    
    
    
}
