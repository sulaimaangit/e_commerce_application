package com.example.user_service.user_service.UserService.UserServiceImpl;

import com.example.user_service.user_service.UserService.AuthService;
import com.example.user_service.user_service.dto.AuthResponse;
import com.example.user_service.user_service.dto.LoginRequest;
import com.example.user_service.user_service.dto.RegisterRequest;
import com.example.user_service.user_service.entity.UserAuth;
import com.example.user_service.user_service.repository.AuthRepo;
import com.example.user_service.user_service.security.JwtService;
import java.util.Locale;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthRepo authRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authRepo = authRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = normalizeEmail(request.email());
        if (authRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already registered");
        }

        UserAuth user = new UserAuth();
        user.setName(request.name().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.password()));

        UserAuth savedUser = authRepo.save(user);
        return toAuthResponse(savedUser, jwtService.generateToken(savedUser));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String email = normalizeEmail(request.email());
        UserAuth user = authRepo.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return toAuthResponse(user, jwtService.generateToken(user));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse currentUser(String email) {
        UserAuth user = authRepo.findByEmail(normalizeEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toAuthResponse(user, null);
    }

    private AuthResponse toAuthResponse(UserAuth user, String token) {
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), token);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
