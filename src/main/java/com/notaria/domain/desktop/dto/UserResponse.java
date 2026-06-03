package com.notaria.domain.desktop.dto;

public record UserResponse(String id,
                           String name,
                           String email,
                           String role,
                           String status) {
}
