package com.app.conjugation.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.conjugation.exceptions.CustomException;
import com.app.conjugation.exceptions.InvalidResetCodeException;
import com.app.conjugation.model.ChangePasswordRequestDTO;
import com.app.conjugation.model.JwtResponseDTO;
import com.app.conjugation.model.LoginResponse;
import com.app.conjugation.model.LoginUserDto;
import com.app.conjugation.model.RefreshToken;
import com.app.conjugation.model.RefreshTokenRequestDTO;
import com.app.conjugation.model.RegisterUserDto;
import com.app.conjugation.model.User;
import com.app.conjugation.service.AuthenticationService;
import com.app.conjugation.service.JwtService;
import com.app.conjugation.service.PasswordResetService;
import com.app.conjugation.service.RefreshTokenService;
import com.app.conjugation.service.ResetCodeService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private PasswordResetService passwordResetService;
    
    @Autowired
	private ResetCodeService resetCodeService;

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
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authenticatedUser.getEmail());
           return JwtResponseDTO.builder()
                   .accessToken(jwtService.generateToken(authenticatedUser))
                   .refreshToken(refreshToken.getToken())
                   .refreshTokenExpiryDate(refreshToken.getExpiryDate().toString())
                   .userId(authenticatedUser.getId())
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
    
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(HttpServletRequest request, @RequestParam("email") String email) throws MailException, MessagingException {
        
    	passwordResetService.resetPassword(request, email);
        
        return ResponseEntity.ok("If an account with that email exists, a password reset link has been processed.");
    }
    
    @PostMapping("/changePassword")
    public String showChangePasswordPage(@RequestBody ChangePasswordRequestDTO changePasswordRequest) {
    	String code = changePasswordRequest.getCode();
    	 if (resetCodeService.validateCode(code)) {
    		 passwordResetService.changePassword(code, changePasswordRequest.getNewPassword());
             return "Password has been reset successfully.";
         } else {
        	 throw new CustomException("Invalid or expired reset code.");
         }
    }

}