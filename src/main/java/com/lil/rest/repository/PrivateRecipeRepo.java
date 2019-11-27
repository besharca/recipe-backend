package com.lil.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.rest.model.PrivateRecipe;

public interface PrivateRecipeRepo extends JpaRepository<PrivateRecipe, Integer>{
	public List<PrivateRecipe> findAllByEmail(String email);
}
