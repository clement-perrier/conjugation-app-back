package com.app.conjugation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import com.app.conjugation.exceptions.CustomException;
import com.app.conjugation.model.ChangePasswordRequestDTO;
import com.app.conjugation.model.JwtResponseDTO;
import com.app.conjugation.model.LoginUserDto;
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

    @PostMapping("/signupAsGuest")
    public ResponseEntity<User> registerAsGuest() {
        User registeredUser = authenticationService.signupAsGuest();
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginUserDto loginUserDto){
        JwtResponseDTO jwtResponse = authenticationService.login(loginUserDto);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/loginAsGuest")
    public ResponseEntity<JwtResponseDTO> loginAsGuest(@RequestBody Integer userId){
        JwtResponseDTO jwtResponse = authenticationService.loginAsGuest(userId);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.refreshToken(refreshTokenRequestDTO.getToken());
    }

    @DeleteMapping("/refreshToken/{refreshToken}")
    public ResponseEntity<String> deleteRefreshToken(@PathVariable String refreshToken) {
        return refreshTokenService.deleteRefreshToken(refreshToken);
    }
    
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(HttpServletRequest request, @RequestParam("email") String email) throws MailException, MessagingException {
        
    	String response = passwordResetService.resetPassword(request, email);
        
        return ResponseEntity.ok(response);
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