package com.fileupload.FileUpload.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtService {
    private String secretKey="";
    public JwtService() throws NoSuchAlgorithmException{
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = keyGen.generateKey();
        secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    public String generateToken(String username){
        Map<String, Objects> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+60*60*60)).and().signWith(getKey()).compact();
    }

    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
