package com.notaria.infraestructure.adapter.persistence.dto;

public record NotariaPersistenceDto (
        Long id,
        String name,
        String nit,
        String direccion,
        String email,
        String categoria,
        String createdAt,
        String updatedAt) {
}
