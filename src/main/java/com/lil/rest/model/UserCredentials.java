package com.lil.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient; 
import javax.validation.constraints.Email; 

import org.hibernate.validator.constraints.Length; 

@Entity
public class UserCredentials {
	
	
	


	@Id
	@GeneratedValue
	private int id;
	
	@Email
	private String email;
	
	private String token;
	
	private String encryptedPassword;
	 
	@Transient
	@Length(min=6, max=15)
	private String password;
	
	@Transient
	@Length(min=6, max=15)
	private String repeatPassword; 
	
	
	
	public UserCredentials(@Email String email, @Length(min = 6, max = 15) String password,
			@Length(min = 6, max = 15) String repeatPassword) {
		super();
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public UserCredentials(int id, @Email String email, @Length(min = 6, max = 15) String password,
			@Length(min = 6, max = 15) String repeatPassword) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public UserCredentials() {
		super();
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		this.password = password;//encode.encode(password);
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;//encode.encode(repeatPassword);
	}


	public boolean isValid() {
		return this.getPassword().equals(this.getRepeatPassword());
	}
	
	

}
