package com.example.user_service.user_service.repository;

import com.example.user_service.user_service.entity.UserAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByEmail(String email);

    boolean existsByEmail(String email);
}
