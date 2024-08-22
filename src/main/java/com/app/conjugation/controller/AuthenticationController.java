package com.app.conjugation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.exceptions.CustomException;
import com.app.conjugation.model.JwtResponseDTO;
import com.app.conjugation.model.LoginResponse;
import com.app.conjugation.model.LoginUserDto;
import com.app.conjugation.model.RefreshToken;
import com.app.conjugation.model.RefreshTokenRequestDTO;
import com.app.conjugation.model.RegisterUserDto;
import com.app.conjugation.model.User;
import com.app.conjugation.service.AuthenticationService;
import com.app.conjugation.service.JwtService;
import com.app.conjugation.service.RefreshTokenService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody LoginUserDto loginUserDto){
    	User authenticatedUser = authenticationService.authenticate(loginUserDto);
        if(authenticatedUser != null){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginUserDto.getEmail());
           return JwtResponseDTO.builder()
                   .accessToken(jwtService.generateToken(authenticatedUser))
                   .refreshToken(refreshToken.getToken())
                   .refreshTokenExpiryDate(refreshToken.getExpiryDate().toString())
                   .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
    
    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user);
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() -> new CustomException("Refresh Token is not in DB..!!"));
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setToken(jwtToken);
//        loginResponse.setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }
}