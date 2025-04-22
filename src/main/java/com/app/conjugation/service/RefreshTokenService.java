package com.app.conjugation.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import com.app.conjugation.exceptions.CustomException;
import com.app.conjugation.model.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    private final JwtService jwtService;
    
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
                optionalExistingRefreshToken.ifPresent(refreshTokenRepository::delete);
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

    public JwtResponseDTO refreshToken (String refreshTokenToken){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshTokenToken);
        if (optionalRefreshToken.isPresent() && !isExpired(optionalRefreshToken.get())){
            RefreshToken refreshToken = optionalRefreshToken.get();
            User user = refreshToken.getUser();
            String accessToken = jwtService.generateToken(user);
            return JwtResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshTokenToken).build();
        } else {
            throw new CustomException("Refresh Token is not in DB..!!");
        }
//        return refreshTokenRepository.findByToken(token)
//                .map(verifyExpiration)
//                .map(RefreshToken::getUser)
//                .map(User user -> {
//                    String accessToken = jwtService.generateToken(user);
//                    return JwtResponseDTO.builder()
//                            .accessToken(accessToken)
//                            .refreshToken(token.build();
//                }).orElseThrow(() -> new CustomException("Refresh Token is not in DB..!!"));
//        return refreshTokenRepository.findByToken(token);
        }

    public Boolean isExpired(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return false;
    }

    public ResponseEntity<String> deleteRefreshToken(String refreshTokenToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenToken)
                .orElseThrow(() -> new RuntimeException("Couldn't delete user refresh token"));

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Couldn't delete user refresh token"));

        user.setRefreshToken(null);
        userRepository.save(user);

        return ResponseEntity.ok("User refresh token deleted successfully");
    }

}