package com.example.library_webapp.repository;

import com.example.library_webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by username.
     *
     * @param username unique username
     * @return Optional User
     */
    Optional<User> findByUsername(String username);
}
