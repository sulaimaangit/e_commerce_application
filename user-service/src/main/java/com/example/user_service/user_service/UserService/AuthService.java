package com.example.user_service.UserService;

import com.example.user_service.dto.AuthResponse;
import com.example.user_service.dto.RegisterRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.dto.LoginRequest;
import java.util.List;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse currentUser(String email);

    List<UserResponse> allUsers();
}
