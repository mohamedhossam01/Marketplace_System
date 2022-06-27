package com.example.marketplace.dao;

import com.example.marketplace.model.Customer;

public interface CustomerDao {

    Customer findCustomerByEmail(String email);
    String hashPassword(String password);
    boolean insertNewCustomer(String email, String password, String fullName);
}
