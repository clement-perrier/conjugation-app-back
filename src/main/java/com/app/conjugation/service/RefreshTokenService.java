package com.app.conjugation.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.*;

import com.app.conjugation.model.RefreshToken;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.RefreshTokenRepository;
import com.app.conjugation.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;
    
    @Value("${security.refresh-token-expiration-time}")
    private long refreshTokenExpiration;

    @Value("${security.refresh-token-remaining-time}")
    private long refreshTokenRemainingTime;

    public RefreshToken createRefreshTokenFromEmail(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<RefreshToken> optionalExistingRefreshToken = refreshTokenRepository.findByUser(user);
            // No need to create new refresh token if still at least 1 year = 31556952000 ms remaining
            if (optionalExistingRefreshToken.isPresent() &&
                    Duration.between(Instant.now(), optionalExistingRefreshToken.get().getExpiryDate()).toMillis() > refreshTokenRemainingTime) {
                return optionalExistingRefreshToken.get();
            } else {
                // Removing existing User refresh token
                optionalExistingRefreshToken.ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
                // Build and save User refresh token in DB
                return generateRefreshToken(user);
            }
        } else {
            throw new RuntimeException("User with this email " + email + " not found.");
        }
    }

    // Build and save User refresh token in DB
    public RefreshToken generateRefreshToken(User user){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
                .build();
        return refreshTokenRepository.save(refreshToken);
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