package com.app.conjugation.model;


import java.util.Collection;
import java.util.List;

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

@Entity
@Table(name = "user")
public class User implements UserDetails {
	
	public User() {
		
	}
	
	public User(Integer id, String firstname, String lastname, LearningLanguage defaultLearningLanguage, List<UserLearningLanguage> userLearningLanguageList){
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setDefaultLearningLanguage(defaultLearningLanguage);
		this.setUserLearningLanguageList(userLearningLanguageList);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	private String fullName;
	
    private String email;

    private String password;
	
	@ManyToOne
    @JoinColumn(name="default_learning_language_id")
	private LearningLanguage defaultLearningLanguage;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserLearningLanguage> userLearningLanguageList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LearningLanguage getDefaultLearningLanguage() {
		return defaultLearningLanguage;
	}

	public void setDefaultLearningLanguage(LearningLanguage lastSelectedLearningLanguage) {
		this.defaultLearningLanguage = lastSelectedLearningLanguage;
	}

	public List<UserLearningLanguage> getUserLearningLanguageList() {
		return userLearningLanguageList;
	}

	public void setUserLearningLanguageList(List<UserLearningLanguage> userLearningLanguageList) {
		this.userLearningLanguageList = userLearningLanguageList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
