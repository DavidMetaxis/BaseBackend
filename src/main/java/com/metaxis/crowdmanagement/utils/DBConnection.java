/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metaxis.crowdservice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author David Anstee
 * Date Created: 12/03/2018
 * 
 * SQL server connection details used to connect to the selected databases.
 */
public class DBConnection {
    private final String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SERVER_DB = "jdbc:sqlserver://",
                        SERVER_DB_NAME = ";databaseName=",
//            SERVER_DB_LIVE = "jdbc:sqlserver://metaxis-db;databaseName=",
//                        SERVER_DB_DEV = "jdbc:sqlserver://metaxis-dev;databaseName=",
//                        SERVER_DB_TRAINING = "jdbc:sqlserver://METAXIS-TRAININ;databaseName=",
//                        SERVER_DB_DAVE = "jdbc:sqlserver://METAXIS-DAVID2;databaseName=",
//                        SERVER_DB_GORDON = "jdbc:sqlserver://METAXIS-GORDON;databaseName=",
//                        SERVER_DB_MIKE = "jdbc:sqlserver://METAXIS-MIKE2;databaseName=",
//                        SERVER_DB_SOHIL = "jdbc:sqlserver://METAXIS-SOHIL;databaseName=",
                        USER_DB_2014_CROWD = ";user=Crowd;password=Jkaklsdnfhnui8865ioB?<---)jgbas#",
                        USER_DB_2014 = ";user=CRS;password=CR5103iN1#?lkjO&&&16*+++1MLKjwOPhgj98Ljjgnjj21";
        
    private Connection con; // global connection to the database used throughout
    private String connection = "", dbName = "";

    /**
     * Constructor
     * 
     * @param connection          Connection string used to connect to database
     * @param dbName              Name of the database to be opened
     */
    public DBConnection(String connection, String dbName)
    {        
        this.connection = connection;
        this.dbName = dbName;
        connectDB(connection, true);
    }
    
    /**
     * Constructor
     * 
     * @param pool          Connection string used to connect to database     
     */
    public DBConnection(String pool)
    {        
        this.connection = pool;        
        connectDB(pool);
    }

    // connect to a database 
    private boolean connectDB(String connection, boolean test)
    {
        try {
            Class.forName(SQL_SERVER_DRIVER);            
            con = DriverManager.getConnection(connection);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean connectDB(String pool) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(pool);
            con = ds.getConnection();
            return true;
        } catch (SQLException | NamingException ex) {
             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Return database connection
     *
     * @return      database connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException
    {
        if(con == null || con.isClosed()) {
            connectDB(connection);
        }
        if(con == null)
            throw new SQLException("Unable to connect to database: " + dbName);
        else 
            return con;
    }
    
}
