package com.example.marketplace.model;

import com.example.marketplace.dao.CustomerDaoJdbc;

public class Customer {
    private long id;
    private String fullName;
    private int cash;
    private String email;
    private String password;


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
		CustomerDaoJdbc dao = new CustomerDaoJdbc();
		dao.setCostumerCashTo(id, cash);
	}
}
