package com.notaria.application.port.out;

import com.notaria.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> buscarTodos();
    void eliminar(Long id);
    boolean existePorEmail(String email);
}
