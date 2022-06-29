package com.example.marketplace.dao;

public class AdminDaoJdbc implements AdminDao{
    @Override
    public int getNumberOfRegisteredAccounts() {
        return 5;
    }

    @Override
    public int getNumberOfSoldItems() {
        return 10;
    }

    @Override
    public int getNumberOfAvailableItems() {
        return 20;
    }
}
