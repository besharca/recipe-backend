package com.lil.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PrivateRecipe {


	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	private String email;
	private String name;
	@Column(length=Integer.MAX_VALUE)
	private String[] imagePath;
	private String description;
	
	@OneToMany(targetEntity=Ingredient.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Ingredient> ingredients;

	
	
	
	public PrivateRecipe() { 
	}
	
	 
	public PrivateRecipe(int id, String email, String name, String[] imagePath, String description, List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.description = description;
		this.ingredients = ingredients;
		this.email = email;
	}
	
	public PrivateRecipe( String name,String email, String[] imagePath, String description, List<Ingredient> ingredients) {
	
		this.name = name;
		this.imagePath = imagePath;
		this.description = description;
		this.ingredients = ingredients;
		this.email = email;
	}



	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getImagePath() {
		return imagePath;
	}
	public void setImagePath(String[] imagePath) {
		this.imagePath = imagePath;
	} 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
