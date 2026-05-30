package com.notaria.domain.desktop.dto;

public record CreateUserRequest (
        String id,
        String name,
        String email,
        String password,
        String role
){}
