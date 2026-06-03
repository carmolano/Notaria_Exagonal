package com.notaria.application.port.in;



import com.notaria.domain.model.Usuario;

import java.util.List;
import java.util.Optional;



public interface UsuarioServicePort {
    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Optional<Usuario> obtenerUsuarioPorEmail(String email);
    List<Usuario> listarUsuarios();
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
}

