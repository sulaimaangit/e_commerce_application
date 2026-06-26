package com.example.user_service.user_service.dto;

public record AuthResponse(
        Long id,
        String name,
        String email,
        String token
) {
}
