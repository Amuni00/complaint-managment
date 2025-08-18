package com.bank.complaint_managment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username; // or email
    private String password;
}
