package com.notaria.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record DeleteNotariaCommand(
        @NotNull(message = "El ID de la notaría a eliminar es requerido")
        Long id
) {}