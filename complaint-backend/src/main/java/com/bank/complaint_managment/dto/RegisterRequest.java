package com.bank.complaint_managment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username; // or email
    private String password;
    private String role; // ADMIN, SUPERVISOR, STAFF
    private Long departmentId; // will be null for ADMIN
}
