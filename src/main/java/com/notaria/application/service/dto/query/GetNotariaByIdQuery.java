package com.notaria.application.service.dto.query;

import jakarta.validation.constraints.NotNull;



public record GetNotariaByIdQuery(
        @NotNull(message = "El ID de la notaría es requerido para la consulta")
                                  Long id) {

}
