package com.quoteExpress.quoteExpress.repository;

import com.quoteExpress.quoteExpress.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(UUID userId);

    User findUserByEmail(String email);

    void deleteById(UUID userID);
}
