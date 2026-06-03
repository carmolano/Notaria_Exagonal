package com.notaria.infrastructure.adapter.in.web.controller;

import com.notaria.domain.model.PoderNotarial;
import com.notaria.domain.port.in.PoderNotarialServicePort;
import com.notaria.infrastructure.adapter.in.web.dto.PoderNotarialDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/poderes")
@RequiredArgsConstructor
public class PoderNotarialController {
    private final PoderNotarialServicePort poderServicePort;

    // POST /api/poderes - Crear poder notarial
    @PostMapping
    public ResponseEntity<PoderNotarialDTO.Response> crearPoder(
            @Valid @RequestBody PoderNotarialDTO.Request request) {
        PoderNotarial poder = toDomain(request);
        PoderNotarial creado = poderServicePort.crearPoder(poder);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(creado));
    }

    // GET /api/poderes - Listar todos
    @GetMapping
    public ResponseEntity<List<PoderNotarialDTO.Response>> listarPoderes() {
        List<PoderNotarialDTO.Response> lista = poderServicePort.listarPoderes()
                .stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // GET /api/poderes/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<PoderNotarialDTO.Response> obtenerPoder(@PathVariable Long id) {
        return poderServicePort.obtenerPoderPorId(id)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/poderes/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<PoderNotarialDTO.Response> actualizarPoder(
            @PathVariable Long id,
            @Valid @RequestBody PoderNotarialDTO.Request request) {
        PoderNotarial poder = toDomain(request);
        PoderNotarial actualizado = poderServicePort.actualizarPoder(id, poder);
        return ResponseEntity.ok(toResponse(actualizado));
    }

    // DELETE /api/poderes/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPoder(@PathVariable Long id) {
        poderServicePort.eliminarPoder(id);
        return ResponseEntity.noContent().build();
    }

    private PoderNotarial toDomain(PoderNotarialDTO.Request r) {
        return PoderNotarial.builder()
                .numeroPoder(r.getNumeroPoder())
                .tipoPoder(r.getTipoPoder())
                .poderdante(r.getPoderdante())
                .apoderado(r.getApoderado())
                .facultades(r.getFacultades())
                .fechaOtorgamiento(r.getFechaOtorgamiento())
                .fechaVencimiento(r.getFechaVencimiento())
                .estado(r.getEstado())
                .notario(r.getNotario())
                .usuarioId(r.getUsuarioId())
                .build();
    }

    private PoderNotarialDTO.Response toResponse(PoderNotarial p) {
        PoderNotarialDTO.Response r = new PoderNotarialDTO.Response();
        r.setId(p.getId());
        r.setNumeroPoder(p.getNumeroPoder());
        r.setTipoPoder(p.getTipoPoder());
        r.setPoderdante(p.getPoderdante());
        r.setApoderado(p.getApoderado());
        r.setFacultades(p.getFacultades());
        r.setFechaOtorgamiento(p.getFechaOtorgamiento());
        r.setFechaVencimiento(p.getFechaVencimiento());
        r.setEstado(p.getEstado());
        r.setNotario(p.getNotario());
        r.setUsuarioId(p.getUsuarioId());
        return r;
    }
}
