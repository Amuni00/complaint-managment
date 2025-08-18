package com.bank.complaint_managment.controller;

import com.bank.complaint_managment.entity.User;
import com.bank.complaint_managment.dto.LoginRequest;
import com.bank.complaint_managment.dto.RegisterRequest;
import com.bank.complaint_managment.dto.JwtResponse;
import com.bank.complaint_managment.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // REGISTER endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    // LOGIN endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.loginUser(request);
        return ResponseEntity.ok(jwtResponse);
    }
}
