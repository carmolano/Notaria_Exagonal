package com.notaria.infraestructure.entrypoint.rest.dto.response;

public record ApiErrorResponse(
        int status,
        String message
) {
}
