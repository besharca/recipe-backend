package com.lil.rest.controllers;
 

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lil.rest.model.Login;
import com.lil.rest.model.LoginResponse;
import com.lil.rest.model.UserCredentials;
import com.lil.rest.repository.UserRepo;
import com.lil.rest.security.JwtGenerator;

@RestController
@RequestMapping
public class RegisterController {

	@Autowired
	private UserRepo userRepo;
	
    private JwtGenerator jwtGenerator;

    public RegisterController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> generate(@RequestBody final UserCredentials user, HttpServletResponse response) { 
    	
    	System.out.println(!userRepo.existsByEmail(user.getEmail()));
    	System.out.println(user.isValid());
    	System.out.println(user.getPassword());
    	System.out.println(user.getRepeatPassword());
    	 
    	if(user.isValid() && !userRepo.existsByEmail(user.getEmail())) {
    		user.setEncryptedPassword(encode.encode(user.getPassword()));
    		user.setToken(jwtGenerator.generate(user,"USER"));
    		userRepo.save(user);
    		
    		return  new ResponseEntity<String>("CREATED",HttpStatus.OK);
    	}
    	 

    	return  new ResponseEntity<String>("EMAIL_EXISTS",HttpStatus.CONFLICT);
    }
    
    
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody Login loginCredentials) {
    	UserCredentials user = userRepo.findByEmail(loginCredentials.getEmail());
    	
    	if(user==null){
    		return new ResponseEntity<LoginResponse>(new LoginResponse(null, "USER_NOT_FOUND"), HttpStatus.UNAUTHORIZED);
    	}
    	
    	
    	
    	if(encode.matches(loginCredentials.getPassword(), user.getEncryptedPassword())) {
    		return new ResponseEntity<LoginResponse>(new LoginResponse(user.getToken(), null),HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<LoginResponse>(new LoginResponse(null, "INVALID_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }
}
