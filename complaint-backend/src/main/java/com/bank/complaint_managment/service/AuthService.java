package com.bank.complaint_managment.service;

import com.bank.complaint_managment.entity.User;
import com.bank.complaint_managment.entity.enums.Role;
import com.bank.complaint_managment.payload.LoginRequest;
import com.bank.complaint_managment.payload.RegisterRequest;
import com.bank.complaint_managment.payload.JwtResponse;
import com.bank.complaint_managment.repository.UserRepository;
import com.bank.complaint_managment.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(RegisterRequest request) {
        // If ADMIN → department is null automatically
        Role role = Role.valueOf(request.getRole().toUpperCase());
        Long deptId = (role == Role.ADMIN) ? null : request.getDepartmentId();

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setDepartmentId(deptId); // assuming you store deptId directly or map to Department entity

        userRepository.save(user);
        return "User registered successfully!";
    }

    public JwtResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new JwtResponse(token, user.getUsername(), user.getRole().name());
    }
}
