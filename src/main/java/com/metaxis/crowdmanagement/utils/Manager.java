/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metaxis.crowdmanagement.exceptions.CrowdExceptions;
import com.metaxis.crowdmanagement.response.ResponseError;
import com.metaxis.crowdmanagement.response.RestResponse;
import com.metaxis.crowdservice.utils.DBCalls;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DAnstee
 */
public class Manager {
    protected Properties properties;
    protected DBCalls dbCalls;
//    public ResponseErrors errors = new ResponseErrors();
    public ResponseError error = new ResponseError();
    protected Response.ResponseBuilder builder;
    private String dbServer;
//    protected Authentication auth = null;
    protected PreparedStatement ps1 = null, ps2 = null;
    
    public Manager() {
        properties = CrowdFunctions.getPropertyFile();
        getDBInit();
    }
    
//    public Manager(HttpHeaders httpHeaders, UriInfo uriInfo, Request request, SecurityContext securityContext, boolean checkAuthentication, boolean runSessionCheck) 
//    {
//        getDBInit(); // connect to the correct DB
//        if(checkAuthentication) // check the authorisation
//            checkAuthentication(httpHeaders, runSessionCheck);            
//    }
    
     // connect to the database
    private void getDBInit()
    {
        String dbType = (String)properties.get(CrowdProps.DATABASE_SERVER);
        if(dbType == null || dbType.isEmpty()) {
            throw new WebApplicationException(setErrorResponse(
                CrowdExceptions.getException("db_connection")));
        } else {
            dbServer = dbType;
            dbCalls = new DBCalls(dbServer); // create the db connection
            return;
        }         
    }
    
    protected void setErrorLog(Exception error)
    {   
        setErrorLog(error.getMessage(), getStackTraceAsString(error));
    }
    
    protected void setErrorLog(String content, String header)
    {   
        int userID = 0;
//        AuthenticationUser user = getUser();
//        if(user != null)
//            userID = user.getUserid();
        try {
            if(!content.isEmpty()) {
                ps1 = dbCalls.getCrowdCon().prepareStatement("INSERT INTO CrowdErrorLog (UserID, Header, Message) VALUES (?, ?, ?)",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ps1.setInt(1, userID);
                ps1.setString(2, header);
                ps1.setString(3, content);
                ps1.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Create a response object to send to the client containing the content 
     * from the RestResponse object
     * 
     * @param pojo          Object to be used in the response
     * @return              Response object containing content to be sent back to the client
     */
    public Response setActionResponse(RestResponse pojo)
    {
        if(pojo == null) { // no response return
//            setErrorLog(error);
            return setErrorResponse(error);
        } else {
            builder = Response.status(Response.Status.OK);
            return setResponse(pojo);
        }
    }
    
     /**
     * Create bad request response object for any failed requests that are 
     * returning a RestResponse Object
     * 
     * @param error         Error object used in the response   
     * @return              Response object containing error content to be sent back to the client
     */
    public Response setErrorResponse(ResponseError error)
    {
        setErrorLog(error.getHeader(), error.getError()); // log the error
        builder = Response.status(Response.Status.BAD_REQUEST);
        return setResponse(error);
    }
    
     /**
     * Create bad request response object for any failed requests that are 
     * returning a String value
     * 
     * @param error         String containing error to be returned to client
     * @return              Response object containing error content to be sent back to the client
     */
    public Response setErrorResponse(String error)
    {
        builder = Response.status(Response.Status.BAD_REQUEST);
        return setResponse(error, MediaType.TEXT_PLAIN);
    }
    
    // set the response to be returned by adding the RestResponse object to the ResponseBuilder
    private Response setResponse(RestResponse pojo)
    {
        try {
            builder.entity(CrowdFunctions.getPojoAsString(pojo));
            builder.type(MediaType.APPLICATION_JSON);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            builder = Response.status(Response.Status.BAD_REQUEST);
            builder.entity(ex.getMessage());
        }
        return builder.build();
    }       
    
    // set the response to be returned by adding the String to the ResponseBuilder
    public Response setResponse(String pojo, String type)
    {
        builder.entity(pojo);
        builder.type(type);
        return builder.build();
    }
    
    /**
     * Close the database connection when the request has ended     
     */
    public void closeConnection()
    {
        closeStatements(ps1);
        closeStatements(ps2);
        if(dbCalls != null) {
            dbCalls.closeConnection();
        }
    }

    // close any statements that are open
    private void closeStatements(PreparedStatement ps)
    {
        if(ps != null) {
            try {
                ps.close();
            } catch(SQLException ex) {}
        }
    }
    
    private String getStackTraceAsString(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
    
}
