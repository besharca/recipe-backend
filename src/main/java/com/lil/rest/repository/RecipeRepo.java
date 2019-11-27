package com.lil.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.rest.model.Recipe;


public interface RecipeRepo extends JpaRepository<Recipe, Integer>{

}
