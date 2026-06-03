package com.notaria.infrastructure.adapter.in.web.controller;
import com.notaria.domain.model.Usuario;
import com.notaria.domain.port.in.UsuarioServicePort;
import com.notaria.infrastructure.adapter.in.web.dto.UsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioServicePort usuarioServicePort;

    // POST /api/usuarios - Crear usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO.Response> crearUsuario(@Valid @RequestBody UsuarioDTO.Request request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(request.getPassword())
                .rol(request.getRol())
                .build();
        Usuario creado = usuarioServicePort.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(creado));
    }

    // GET /api/usuarios - Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioDTO.Response>> listarUsuarios() {
        List<UsuarioDTO.Response> lista = usuarioServicePort.listarUsuarios()
                .stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // GET /api/usuarios/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> obtenerUsuario(@PathVariable Long id) {
        return usuarioServicePort.obtenerUsuarioPorId(id)
                .map(u -> ResponseEntity.ok(toResponse(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/usuarios/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> actualizarUsuario(@PathVariable Long id,
                                                                 @Valid @RequestBody UsuarioDTO.Request request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(request.getPassword())
                .rol(request.getRol())
                .build();
        Usuario actualizado = usuarioServicePort.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(toResponse(actualizado));
    }

    // DELETE /api/usuarios/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioServicePort.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO.Response toResponse(Usuario u) {
        UsuarioDTO.Response r = new UsuarioDTO.Response();
        r.setId(u.getId());
        r.setNombre(u.getNombre());
        r.setEmail(u.getEmail());
        r.setRol(u.getRol());
        return r;
    }
}
