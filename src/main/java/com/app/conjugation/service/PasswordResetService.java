package com.app.conjugation.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.app.conjugation.model.PasswordResetToken;
import com.app.conjugation.exceptions.ConflictException;
import com.app.conjugation.exceptions.UserNotFoundException;
import com.app.conjugation.model.LoginUserDto;
import com.app.conjugation.model.RegisterUserDto;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.PasswordTokenRepository;
import com.app.conjugation.repository.ResetCodeRepository;
import com.app.conjugation.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordTokenRepository passwordTokenRepository;
	
	@Autowired
	private ResetCodeService resetCodeService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    private ResetCodeRepository resetCodeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void resetPassword(HttpServletRequest request, String email) throws MailException, MessagingException {
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User with email " + email + " not found");
		}
		
		User user = optionalUser.get();
		
		String code = resetCodeService.generateUniqueCode(user);
//		
//		Optional<PasswordResetToken> optionalToken = passwordTokenRepository.findByUser(user);
//		
//		String token = "";
//		
//		// If token already exist and not expired => returning existing token
//		if(optionalToken.isPresent() && !isTokenExpired(optionalToken.get())) {
//			token = optionalToken.get().getToken();
//		} else {
//			// Token exist but is expired => removing the token from DB
//			if(isTokenExpired(optionalToken.get())) {
//				passwordTokenRepository.delete(optionalToken.get());
//			}
//			// New token generated
//			token = UUID.randomUUID().toString();
//			createPasswordResetTokenForUser(user, token);
//		}

		// Sending email
		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), code, email));

	}
	
	public void changePassword(String code, String newPassword) {
		User user = resetCodeRepository.findByCode(code).getUser();
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//		return "conjugationapp://";
	}

	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);
	}

	private SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String code, String email) throws MessagingException {
//		String url = contextPath + "/auth/changePassword?token=" + token;
//		String url = "<a href=\"http://localhost:8081\">Reset Your Password</a>";
		String message = "Copy the following code to reset your password: " + code ;
		return constructEmail("Reset Password", message + " \r\n", email);
	}

	private SimpleMailMessage constructEmail(String subject, String body, String email) throws MessagingException {
		SimpleMailMessage emailToSend = new SimpleMailMessage();
		emailToSend.setSubject(subject);
		emailToSend.setText(body);
		emailToSend.setTo(email);
		return emailToSend;
	}

	public String validatePasswordResetToken(String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token).get();

		return !isTokenFound(passToken) ? "invalidToken" : isTokenExpired(passToken) ? "expired" : null;
	}

	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {
		return passToken.getExpiryDate().isBefore(Instant.now());
	}

}