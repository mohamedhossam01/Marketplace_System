package com.example.marketplace.model;

import java.util.ArrayList;

public class Item {
    private long id;
    private String name;
    private int price;
    private ArrayList<String> categories;

    public Item(){

    }

    public Item(long id, String name, int price, ArrayList<String> categories){
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
