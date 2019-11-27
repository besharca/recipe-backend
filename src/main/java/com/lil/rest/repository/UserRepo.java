package com.lil.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.rest.model.UserCredentials;

public interface UserRepo extends JpaRepository<UserCredentials, Integer>{

	public boolean existsByEmail(String email);
	public UserCredentials findByEmail(String email);
	public UserCredentials findByToken(String token);
	public boolean existsByToken(String token);
	
}
