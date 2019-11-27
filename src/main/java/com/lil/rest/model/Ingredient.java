package com.lil.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@GeneratedValue
	@Id
	private int id;
	private String name;
	private int amount;
	
	public Ingredient(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	public Ingredient() { 
	}
	public Ingredient(int id, String name, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
	}
	
	
	
}
