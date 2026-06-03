package com.notaria.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "poderes_notariales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PoderNotarialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_poder", nullable = false, unique = true, length = 50)
    private String numeroPoder;

    @Column(name = "tipo_poder", nullable = false, length = 50)
    private String tipoPoder;

    @Column(nullable = false, length = 200)
    private String poderdante;

    @Column(nullable = false, length = 200)
    private String apoderado;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String facultades;

    @Column(name = "fecha_otorgamiento", nullable = false)
    private LocalDate fechaOtorgamiento;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 200)
    private String notario;

    @Column(name = "usuario_id")
    private Long usuarioId;




}
