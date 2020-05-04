/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdservice.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author David Anstee
 * Date Created: 12/03/2018
 * 
 * Create a database connection for each database used within the Crowd tool on
 * the selected server/local machine.
 */
public class DBCalls {    
    private Connection crowdCon = null;
//    public static final int LIVE_DB = 1, TRAINING_DB = 2, DEV_DB = 3, 
//                    DAVE_DB = 4, GORDON_DB = 5, MIKE_DB = 6, SOHIL_DB = 7;
    public static final String DEV_DB = "METAXIS-SEARCH";
//    private static String CROWD_DB = "Crowd";
    private static String CROWD_DB = "CrowdCochrane";
    private static String JDBC_DB = "jdbc/";
    protected String dbServer = "METAXIS-SEARCH"; // default to dev db
    private boolean test = true;
    
    public DBCalls(String dbServer)            
    {
        this.dbServer = dbServer;
    }

    /**
     * Returns a database connection for the selected database location
     * 
     * @return                      A database connection
     * @throws SQLException 
     */
    public Connection getCrowdCon() throws SQLException {
        if(crowdCon == null || crowdCon.isClosed()) {
            if(!test) {            
                crowdCon = new DBConnection(JDBC_DB + CROWD_DB).getConnection();
            } else {    
                crowdCon = new DBConnection(getDBConnection(dbServer, CROWD_DB), CROWD_DB).getConnection();
            }
        }
        return crowdCon;
    }     
    
    // get the live database connection
    protected String getDBConnection(String dbServer, String dbName)
    {
        return DBConnection.SERVER_DB  + dbServer + DBConnection.SERVER_DB_NAME + dbName + DBConnection.USER_DB_2014_CROWD;
    }
        
    /**
     * close a prepared statement 
     * 
     * @param ps        Prepared statement to be closed.
     */
    public void closeStatement(PreparedStatement ps)
    {
        try {
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
        }
    }
    
    /**
     * Return the name of the current database location
     * 
     * @return          DB location name
     */
    public String getDBName()
    {
        return dbServer;
    }
    
    /**
     * Close the connections to all open databases
     */
    public void closeConnection()
    {
        try {            
            if(crowdCon != null) {
                crowdCon.close();
                crowdCon = null;
            }
        } catch (SQLException ex) {
        }
    }
    

    public void setTest(boolean test) {
        this.test = test;
    }
    
    
    
}
