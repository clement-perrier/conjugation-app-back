package com.app.conjugation.repository;

import java.util.Optional;

import com.app.conjugation.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.conjugation.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u " + "where u.id = :userId")
	User findByUserAndLanguage(@Param("userId") Integer userid);
	
	Optional<User> findByEmail(String email);

	Optional<User> findByRefreshToken(RefreshToken refreshToken);

	
}
