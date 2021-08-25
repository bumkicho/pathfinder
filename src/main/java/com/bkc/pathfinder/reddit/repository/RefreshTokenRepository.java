package com.bkc.pathfinder.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkc.pathfinder.reddit.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}