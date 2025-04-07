package com.app.conjugation.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    @Getter
    private final Integer userId;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Integer userId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userId);  // This will return the userId as the username
    }

    public String getUserId() {
        return String.valueOf(userId);  // This will return the userId as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // You can add logic here to check for account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // You can add logic here to check if the account is locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // You can add logic here to check for credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true;  // You can add logic here to check if the account is enabled
    }

}
