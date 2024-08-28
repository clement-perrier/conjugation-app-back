package com.app.conjugation.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.*;

import com.app.conjugation.model.RefreshToken;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.RefreshTokenRepository;
import com.app.conjugation.repository.UserRepository;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;
    
    @Value("${security.refresh-token-expiration-time}")
    private long refreshTokenExpiration;

    public RefreshToken createRefreshToken(String email){
    	User user = userRepository.findByEmail(email).get();
    	RefreshToken existingRefreshToken = refreshTokenRepository.findByUser(user).get();
    	if(existingRefreshToken != null && existingRefreshToken.getExpiryDate().compareTo(Instant.now())>0) {
    		return existingRefreshToken;
    	} else {
    		if(existingRefreshToken != null) {
    			refreshTokenRepository.delete(existingRefreshToken);
    		}
    		RefreshToken refreshToken = RefreshToken.builder()
                    .user(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(refreshTokenExpiration)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file 
                    .build();
            return refreshTokenRepository.save(refreshToken);
    	}
        
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}