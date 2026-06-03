
package com.notaria.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateNotariaCommand(
        @NotNull(message = "El ID de la notaría es requerido")
        Long id,

        @NotBlank(message = "El nombre de la notaría es requerido")
        String name,

        @NotBlank(message = "El NIT es requerido")
        String nit,

        @NotBlank(message = "La dirección es requerida")
        String direccion,

        @NotBlank(message = "El correo institucional es requerido")
        @Email(message = "El formato del correo es inválido")
        String email,

        @NotBlank(message = "La categoría es requerida")
        String categoria
) {}