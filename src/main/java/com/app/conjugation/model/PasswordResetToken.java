
package com.app.conjugation.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

	private static final int EXPIRATION = 15 * 60 * 1000;
	
	public PasswordResetToken() {}
	 
    public PasswordResetToken(String token, User user) {
		this.setToken(token);
		this.user = user;
		this.setExpiryDate(Instant.now().plusMillis(EXPIRATION));
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    private String token;
 
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
 
    private Instant expiryDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
}