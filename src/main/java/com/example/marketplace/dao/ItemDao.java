package com.example.marketplace.dao;

import com.example.marketplace.model.Customer;
import com.example.marketplace.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao {
    ArrayList<Item> findItemByNameAndCategory(String name, String category);
    ArrayList<Item> findItemByName(String name);
    ArrayList<Item> findItemByCategory(String category);
    ArrayList<Item> getCartContentsFor(Long costumerId);
    ArrayList<Item> getAvailableItems();
    ArrayList<Item> getHistoryFor(long customerId);
    boolean setToPaid(Long customerId, Long itemId);
    void setToAdded(Long customerId, Long itemId, boolean state) throws SQLException;

}
