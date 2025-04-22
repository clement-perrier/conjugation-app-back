package com.app.conjugation.model;


import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements UserDetails {
	
	public User() {}
	
	public User(Integer id, LearningLanguage defaultLearningLanguage, List<UserLearningLanguage> userLearningLanguageList){
		this.id = id;
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setUserLearningLanguageList(userLearningLanguageList);
	}
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String email;

    private String password;
    
    private String deviceToken;
	
	private boolean isGuest;

    @ManyToOne
    @JoinColumn(name="default_learning_language_id")
	private LearningLanguage defaultLearningLanguage;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserLearningLanguage> userLearningLanguageList;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
