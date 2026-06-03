package com.notaria.infrastructure.adapter.persistence.mapper;


import com.notaria.domain.model.Usuario;
import com.notaria.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .rol(entity.getRol())
                .build();
    }

    public UsuarioEntity toEntity(Usuario domain) {
        return UsuarioEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .rol(domain.getRol())
                .build();
    }
}
