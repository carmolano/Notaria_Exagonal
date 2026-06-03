package com.notaria.application.usecase.usuario;




import com.notaria.domain.model.Usuario;
import com.notaria.domain.port.in.UsuarioServicePort;
import com.notaria.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class UsuarioUseCase implements UsuarioServicePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepositoryPort.existePorEmail(usuario.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("CLIENTE");
        }
        return usuarioRepositoryPort.guardar(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositoryPort.buscarPorId(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepositoryPort.buscarPorEmail(email);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositoryPort.buscarTodos();
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        if (usuario.getRol() != null && !usuario.getRol().isBlank()) {
            existente.setRol(usuario.getRol());
        }
        return usuarioRepositoryPort.guardar(existente);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuarioRepositoryPort.eliminar(id);
    }
}
