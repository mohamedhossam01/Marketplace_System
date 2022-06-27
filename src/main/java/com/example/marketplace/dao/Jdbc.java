package com.example.marketplace.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    static Connection getConnection() {
        Connection conn =null;
        try{
             conn = DriverManager.getConnection("jdbc:mysql://localhost/fms","fms", "fms");
        }
        catch(SQLException ex){
            System.err.println("couldn't open connection with mysql database");
            ex.printStackTrace();
        }
        return conn;
    }

    static void closeConnection(Connection conn){
        if(conn == null)
            return;

        try{
            conn.close();
        }
        catch(SQLException ex){
            System.err.println("Couldn't close the connection with mysql database");
        }
    }
}
