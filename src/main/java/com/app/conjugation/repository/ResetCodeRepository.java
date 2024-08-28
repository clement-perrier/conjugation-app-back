package com.app.conjugation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.conjugation.model.ResetCode;
import com.app.conjugation.model.User;

@Repository
public interface ResetCodeRepository extends JpaRepository<ResetCode, String> {
    ResetCode findByCode(String code);
    ResetCode findByUser(User user);
}