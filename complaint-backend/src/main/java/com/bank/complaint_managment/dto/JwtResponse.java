package com.bank.complaint_managment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String role;
}
