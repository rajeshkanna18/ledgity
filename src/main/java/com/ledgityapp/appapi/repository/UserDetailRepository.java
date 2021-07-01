package com.ledgityapp.appapi.repository;

import com.ledgityapp.appapi.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findByUsername(String username);
}
