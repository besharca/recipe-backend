package com.lil.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lil.rest.model.PrivateRecipe;
import com.lil.rest.model.Recipe;
import com.lil.rest.repository.PrivateRecipeRepo;
import com.lil.rest.repository.RecipeRepo;
import com.lil.rest.repository.UserRepo;
 
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class RecipeController {

	@Autowired
	RecipeRepo recipeRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PrivateRecipeRepo privRecipeRepo;

	@GetMapping("/recipes")
	public List<Recipe> getCars(HttpServletRequest request) { 
		return this.recipeRepo.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, name = "/recipes")
	@ResponseBody
	public Recipe whatever(@RequestBody Recipe recipe) {
		this.recipeRepo.save(recipe);
	 
		return this.recipeRepo.findById(recipe.getId()).orElse(null); 
	}

	@PutMapping("/recipes/{id}")
	public Recipe update(@RequestBody Recipe newRecipe, @PathVariable int id) {
		Recipe oldRecipe = this.recipeRepo.findById(id).orElse(null);

		if (oldRecipe != null) {

			oldRecipe.setDescription(newRecipe.getDescription());
			oldRecipe.setImagePath(newRecipe.getImagePath());
			oldRecipe.setIngredients(newRecipe.getIngredients());
			oldRecipe.setName(newRecipe.getName());
			return this.recipeRepo.save(oldRecipe);
		} else {

			return this.recipeRepo.save(newRecipe);
		}

	}

	@DeleteMapping("/recipes/{id}/delete")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable int id) {
		this.recipeRepo.deleteById(id);

		return new ResponseEntity<>("Recipe was deleted successfully", HttpStatus.OK);

	}
	
	
	//###########################	SECURED	###########################
	
	@GetMapping("/secured/recipes")
	public List<PrivateRecipe> getSecuredRecipes(HttpServletResponse res, @RequestHeader("Authorization") String token) { 
		
		
		return this.privRecipeRepo.findAllByEmail(this.userRepo.findByToken(token.substring(6)).getEmail());
	}
	
	@PostMapping("/secured/recipes")
	@ResponseBody
	public PrivateRecipe newSecuredRecipe(@RequestBody PrivateRecipe recipe, @RequestHeader("Authorization") String token) {
		
		recipe.setEmail(this.userRepo.findByToken(token.substring(6)).getEmail());
	 
		return this.privRecipeRepo.save(recipe); 
	}
	
	@DeleteMapping("/secured/recipes/{id}/delete")
	public @ResponseBody ResponseEntity<String> deletePrivate(@PathVariable int id, @RequestHeader("Authorization") String token) {
	 
		if(privRecipeRepo.findById(id).isPresent() 
				&& this.userRepo.findByToken(token.substring(6)).getEmail().equals(this.privRecipeRepo.findById(id).get().getEmail())) {

			this.privRecipeRepo.deleteById(id);
					
			return new ResponseEntity<>("Recipe was deleted successfully", HttpStatus.OK);
		}
		

		return new ResponseEntity<>("Illegal request", HttpStatus.BAD_REQUEST);

	}
	
	@PutMapping("/secured/recipes/{id}")
	public PrivateRecipe updatePrivate(@RequestBody PrivateRecipe newRecipe, @PathVariable int id, @RequestHeader("Authorization") String token) {
		PrivateRecipe oldRecipe = this.privRecipeRepo.findById(id).orElse(null);
 
		
		if (oldRecipe != null && 
				oldRecipe.getEmail().equals(this.userRepo.findByToken(token.substring(6)).getEmail())) 
		{

			oldRecipe.setDescription(newRecipe.getDescription());
			oldRecipe.setImagePath(newRecipe.getImagePath());
			oldRecipe.setIngredients(newRecipe.getIngredients());
			oldRecipe.setName(newRecipe.getName());
			return this.privRecipeRepo.save(oldRecipe);
		} else { 
			return this.privRecipeRepo.save(newRecipe);
		}

	}

}
