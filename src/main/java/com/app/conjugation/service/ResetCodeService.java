package com.app.conjugation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.conjugation.model.ResetCode;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.ResetCodeRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class ResetCodeService {
	
	private static final String CHARACTERS = "0123456789";
    private static final int CODE_LENGTH = 4;
    private static final SecureRandom random = new SecureRandom();

    @Autowired
    private ResetCodeRepository resetCodeRepository;

    private static final int EXPIRATION_MINUTES = 5;

    public String generateUniqueCode(User user) {
    	
    	ResetCode existingResetCode = resetCodeRepository.findByUser(user);
        String code;

        if (existingResetCode != null && validateCode(existingResetCode.getCode())) {
            // Use the existing valid code
            return existingResetCode.getCode();
        } else {
            // Delete the old code if it exists
            if (existingResetCode != null) {
                resetCodeRepository.delete(existingResetCode);
            }
            
            // Generate a new unique code
            do {
                code = generateCode();
            } while (resetCodeRepository.findByCode(code) != null);
        }

        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
        ResetCode resetCode = new ResetCode(code, expirationDate, user);
        resetCodeRepository.save(resetCode);

        return code;
    }

    public boolean validateCode(String code) {
        ResetCode resetCode = resetCodeRepository.findByCode(code);
        if (resetCode != null && resetCode.getExpirationDate().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }
    
    public static String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
