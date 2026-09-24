package com.example.user_service.user_service.dto;

import java.time.Instant;

public record UserResponse(Long id, String name, String email, String role, Instant createdAt) {
}
