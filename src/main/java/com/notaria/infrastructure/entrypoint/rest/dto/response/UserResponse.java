package com.notaria.infraestructure.entrypoint.rest.dto.response;

public record UserResponse(
        String id,
        String name,
        String email,
        String role,
        String status
) {
}
