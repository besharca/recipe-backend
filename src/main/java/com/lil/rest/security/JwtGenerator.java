package com.lil.rest.security;

import org.springframework.stereotype.Component;

import com.lil.rest.model.JwtUser;
import com.lil.rest.model.UserCredentials;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtGenerator {


    public String generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
    
    public String generate(UserCredentials user, String role) {


        Claims claims = Jwts.claims()
                .setSubject(user.getEmail());
        claims.put("password", user.getPassword());
        claims.put("role", role);


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
