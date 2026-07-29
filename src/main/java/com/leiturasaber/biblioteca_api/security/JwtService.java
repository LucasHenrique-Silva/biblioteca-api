package com.leiturasaber.biblioteca_api.security;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service

public class JwtService {

    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SecurityConstants.SECRET_KEY.getBytes());

    }

    public String gerarToken(String email){
        String token = Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public boolean validaToken(String token){

         try {
             Jwts.parser()
                     .verifyWith(getSigningKey())
                     .build()
                     .parseSignedClaims(token);
             return true;
         }catch (JwtException e){
             return false;
         }

    }

    public String extrairEmail(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
