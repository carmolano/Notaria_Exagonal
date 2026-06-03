package com.notaria.infrastructure.adapter.in.web.controller;
import com.notaria.domain.port.out.UsuarioRepositoryPort;
import com.notaria.infrastructure.adapter.in.web.dto.UsuarioDTO;
import com.notaria.infrastructure.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO.LoginResponse> login(@Valid @RequestBody UsuarioDTO.LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generarToken(request.getEmail());
        var usuario = usuarioRepositoryPort.buscarPorEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(
                new UsuarioDTO.LoginResponse(token, usuario.getEmail(), usuario.getNombre(), usuario.getRol())
        );
    }
}
