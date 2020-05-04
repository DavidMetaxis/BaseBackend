/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdmanagement.rest;

import com.metaxis.crowdmanagement.tasktraining.TaskTrainingManager;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.GET;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Path;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *  REST Web Service for crowd calls
 * 
 * @author David Anstee
 * @since 01/05/2020
 * @version 1.0
 * 
 * ----UPDATES----
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@interface CORSBindingFolder {
}

@Provider
@Priority(Priorities.HEADER_DECORATOR)
@CORSBindingFolder
class CrowdCrossDomainFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext creq, ContainerResponseContext cres) {
        Logger.getLogger("com.example").log(Level.INFO, "before: {0}", cres.getHeaders());
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, userID, sessionID");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, TRACE, GET, POST, PUT, DELETE, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
        Logger.getLogger("com.example").log(Level.INFO, "after: {0}", cres.getHeaders());
    }
}

@Provider
class CrowdResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, userID, sessionID");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, TRACE, GET, POST, PUT, DELETE, HEAD");
    }
}

@Path("")
@Produces("application/json")
public class CrowdManagementService {    

    public CrowdManagementService() {
    }

    @GET
    @Path("/record/example")
    public Response getRecordExample(@QueryParam("type") String type,
            @Context HttpHeaders httpHeaders, @Context UriInfo uriInfo, @Context Request request,
            InputStream inputStream, @Context SecurityContext securityContext) {
        TaskTrainingManager manager = new TaskTrainingManager();
        return manager.setActionResponse(manager.getExampleRecord(type));
    }
    
}
