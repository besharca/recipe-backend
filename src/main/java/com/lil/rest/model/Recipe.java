package com.lil.rest.model;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

	public List<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	private String name;
	private String imagePath;
	private String description;
	
	@OneToMany(targetEntity=Ingredient.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Ingredient> ingredients;

	
	
	
	public Recipe() { 
	}
	
	 
	public Recipe(int id, String name, String imagePath, String description, List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.description = description;
		this.ingredients = ingredients;
	}
	
	public Recipe( String name, String imagePath, String description, List<Ingredient> ingredients) {
	
		this.name = name;
		this.imagePath = imagePath;
		this.description = description;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	} 

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
