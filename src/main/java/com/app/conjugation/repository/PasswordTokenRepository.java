package com.app.conjugation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.conjugation.model.PasswordResetToken;
import com.app.conjugation.model.User;

@Repository
public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser(User user);
}