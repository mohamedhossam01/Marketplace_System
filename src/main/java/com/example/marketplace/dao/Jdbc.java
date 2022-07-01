package com.example.marketplace.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    public static String ip;
    static Connection getConnection() {
        Connection conn =null;
        if (ip == null)
            ip = "localhost";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://"+ ip +"/marketplace","marketplace", "marketplace");
        }
        catch(SQLException ex){
            System.err.println("Couldn't open connection with mysql database");
            ex.printStackTrace();
        }
        return conn;
    }

    public static void setIP(String ip){
        Jdbc.ip = ip;
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