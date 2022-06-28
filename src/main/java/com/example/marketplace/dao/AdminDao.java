package com.example.marketplace.dao;

import com.example.marketplace.model.Customer;

public interface AdminDao {
    int getNumberOfRegisteredAccounts();
    int getNumberOfSoldItems();
    int getNumberOfAvailableItems();
}
