package com.notaria.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

public class PoderNotarialDTO {
    @Data
    public static class Request {
        @NotBlank(message = "El número de poder es obligatorio")
        private String numeroPoder;

        @NotBlank(message = "El tipo de poder es obligatorio")
        private String tipoPoder;          // GENERAL, ESPECIAL, REVOCACION

        @NotBlank(message = "El poderdante es obligatorio")
        private String poderdante;

        @NotBlank(message = "El apoderado es obligatorio")
        private String apoderado;

        @NotBlank(message = "Las facultades son obligatorias")
        private String facultades;

        @NotNull(message = "La fecha de otorgamiento es obligatoria")
        private LocalDate fechaOtorgamiento;

        private LocalDate fechaVencimiento;

        private String estado;             // VIGENTE, REVOCADO, VENCIDO

        @NotBlank(message = "El notario es obligatorio")
        private String notario;

        private Long usuarioId;
    }

    @Data
    public static class Response {
        private Long id;
        private String numeroPoder;
        private String tipoPoder;
        private String poderdante;
        private String apoderado;
        private String facultades;
        private LocalDate fechaOtorgamiento;
        private LocalDate fechaVencimiento;
        private String estado;
        private String notario;
        private Long usuarioId;
    }
}
