package com.example.marketplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoJdbc implements AdminDao{
    @Override
    public int getNumberOfRegisteredAccounts(){
        String sql = "SELECT COUNT(*) FROM CUSTOMERS";
        Connection conn = null;
        int count = 0;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return count;
    }

    @Override
    public int getNumberOfSoldItems() {
        String sql = "SELECT COUNT(*) FROM BUY WHERE PAID = 1";
        Connection conn = null;
        int count = 0;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return count;
    }

    @Override
    public int getNumberOfAvailableItems() {
        String sql = "SELECT COUNT(*) FROM ITEMS WHERE ID NOT IN (SELECT ITEM_ID FROM BUY)";
        Connection conn = null;
        int count = 0;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return count;
    }
}
