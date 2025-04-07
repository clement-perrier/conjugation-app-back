package com.app.conjugation.service;

import java.util.Optional;

import com.app.conjugation.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.conjugation.exceptions.ConflictException;
import com.app.conjugation.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public User signup(RegisterUserDto userDto) {
    	
    	 // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            // Throw an exception or return an error response when email is already taken
            throw new ConflictException("Email already in use.");
        }
        
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGuest(false);

        return userRepository.save(user);
    }

    public User signupAsGuest(){
        User user = new User();
        user.setGuest(true);
        return userRepository.save(user);
    }

    public JwtResponseDTO login(LoginUserDto loginUserDto) {
        User authenticatedUser = authenticate(loginUserDto);
        if (authenticatedUser != null) {
            RefreshToken refreshToken = refreshTokenService.createRefreshTokenFromEmail(authenticatedUser.getEmail());
            return getJwtResponseDTO(authenticatedUser, refreshToken);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    public JwtResponseDTO loginAsGuest(Integer userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            // Creating refresh token for user
            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
            // Returning JWT response (access token, refresh token, refresh token expiry date and userID
            return getJwtResponseDTO(user, refreshToken);
        } else {
            throw new RuntimeException("Tried to login as guest but user not found (find by ID)");
        }
    }

    public JwtResponseDTO getJwtResponseDTO(User authenticatedUser, RefreshToken refreshToken) {
        return JwtResponseDTO.builder()
                .accessToken(jwtService.generateToken(authenticatedUser))
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiryDate(refreshToken.getExpiryDate().toString())
                .userId(authenticatedUser.getId())
                .build();

    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.getEmail(),
                    input.getPassword()
            )
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
    
}