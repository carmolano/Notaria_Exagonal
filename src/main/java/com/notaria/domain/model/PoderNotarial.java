package com.notaria.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PoderNotarial {
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
