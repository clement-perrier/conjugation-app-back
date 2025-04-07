package com.app.conjugation.model;


import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserLearningLanguage> userLearningLanguageList;


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
