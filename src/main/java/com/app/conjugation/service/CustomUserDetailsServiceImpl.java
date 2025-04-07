package com.app.conjugation.service;

import com.app.conjugation.model.CustomUserDetails;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Default load by username (email)
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User loadUserById(String userId) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
