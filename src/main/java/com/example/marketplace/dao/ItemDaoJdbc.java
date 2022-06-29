package com.example.marketplace.dao;

import com.example.marketplace.model.Customer;
import com.example.marketplace.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDaoJdbc implements  ItemDao{
    @Override
    public ArrayList<Item> findItemByNameAndCategory(String name, String category) {
        String sql = "select DISTINCT *\n" +
                "FROM items, items_category, categories\n" +
                "WHERE categories.name LIKE \"% " + category + "%\"\n" +
                "AND items.name LIKE \"% " + name +  "%\"\n" +
                "AND items_category.item_id = items.id\n" +
                "AND items_category.category_id = categories.id\n" +
                "AND items.id not in(" +
                "   select item_id" +
                "   from buy" +
                ");";
        Connection conn = null;
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long item_id = rs.getLong("id");
                String item_name = rs.getString("name");
                int item_price  = rs.getInt("price");
                Item item = new Item(item_id, item_name, item_price);
                items.add(item);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return items;
    }

    @Override
    public ArrayList<Item> findItemByName(String name) {
        String sql = "select *\n" +
                "FROM items\n" +
                "WHERE items.name LIKE \"% " + name + "%\"" +
                "AND items.id not in(" +
                "   select item_id" +
                "   from buy" +
                ");";
        Connection conn = null;
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long item_id = rs.getLong("id");
                String item_name = rs.getString("name");
                int item_price  = rs.getInt("price");
                Item item = new Item(item_id, item_name, item_price);
                items.add(item);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return items;
    }

    @Override
    public ArrayList<Item> findItemByCategory(String category) {
        String sql = "select DISTINCT *\n" +
                "FROM items, items_category, categories\n" +
                "WHERE categories.name LIKE \"% " + category + "%\"\n" +
                "AND items_category.item_id = items.id\n" +
                "AND items_category.category_id = categories.id" +
                "AND items.id not in(" +
                "   select item_id" +
                "   from buy" +
                ");";
        Connection conn = null;
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long item_id = rs.getLong("id");
                String item_name = rs.getString("name");
                int item_price  = rs.getInt("price");
                Item item = new Item(item_id, item_name, item_price);
                items.add(item);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return items;
    }

    @Override
    public ArrayList<Item> getCartContentsFor(Long costumerId) {
        String sql = "SELECT * \n" +
                "FROM items, buy\n" +
                "WHERE buy.customer_id =" + costumerId + "\n" +
                "AND buy.item_id = items.id\n" +
                "And buy.paid = \"0\" ;";
        Connection conn = null;
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long item_id = rs.getLong("id");
                String item_name = rs.getString("name");
                int item_price  = rs.getInt("price");
                Item item = new Item(item_id, item_name, item_price);
                items.add(item);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return items;
    }

    @Override
    public boolean setToPaid(Long customerId, Long itemId){
        String sql = "UPDATE buy\n" +
                "SET paid = \"1\"\n" +
                "WHERE customer_id = " + customerId +"\n" +
                "AND  item_id =" + itemId + ";";
        Connection conn = null;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return true;
    };

    @Override
    public boolean setToAdded(Long customerId, Long itemId, boolean state)
    {
        String sql;
        if(state)
        {
            sql = "INSERT INTO buy (customer_id, item_id, paid) VALUES (" + customerId + ", " + itemId + ", \"0\");";
        }
        else
        {
            sql = "DELETE FROM buy WHERE customer_id = " + customerId + " AND item_id = " + itemId + ";";
        }
        Connection conn = null;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return true;
    }
}
