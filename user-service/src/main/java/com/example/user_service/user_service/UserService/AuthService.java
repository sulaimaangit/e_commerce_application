package com.example.user_service.user_service.UserService;

import com.example.user_service.user_service.dto.AuthResponse;
import com.example.user_service.user_service.dto.LoginRequest;
import com.example.user_service.user_service.dto.RegisterRequest;
import com.example.user_service.user_service.dto.UserResponse;
import java.util.List;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse currentUser(String email);

    List<UserResponse> allUsers();
}
