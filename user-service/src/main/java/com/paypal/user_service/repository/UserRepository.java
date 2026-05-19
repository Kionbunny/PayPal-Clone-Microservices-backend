package com.paypal.user_service.repository;

// jpa repository provides CRUD operations
//Optional is 👉 Optional<T> ispackage com.paypal.user_service.repository;

import com.paypal.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing database operations on User entities.
 *
 * Spring Data JPA automatically creates the implementation for this interface
 * at runtime, so we do not need to write the database query code manually.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their email address.
     *
     * Spring Data JPA understands this method name automatically:
     * "findByEmail" means it will search the User table using the email field.
     *
     * Optional<User> is used because a user with the given email may or may not exist.
     *
     * @param email the email address to search for
     * @return an Optional containing the User if found, otherwise an empty Optional
     */

}