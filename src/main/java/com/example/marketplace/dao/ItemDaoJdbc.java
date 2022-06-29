package com.example.marketplace.dao;

import com.example.marketplace.model.Customer;
import com.example.marketplace.model.Item;

import java.util.ArrayList;

public class ItemDaoJdbc implements  ItemDao{
    @Override
    public ArrayList<Item> findItemByNameAndCategory(String name, String category) {
        return null;
    }

    @Override
    public ArrayList<Item> findItemByName(String name) {
        return null;
    }

    @Override
    public ArrayList<Item> findItemByCategory(String category) {
        return null;
    }

    @Override
    public ArrayList<Item> getCartContentsFor(Long costumerId) {
        return null;
    }
}
