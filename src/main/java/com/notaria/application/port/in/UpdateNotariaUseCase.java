package com.notaria.application.port.in;


import com.notaria.domain.model.NotariaModel;

public interface UpdateNotariaUseCase {
    NotariaModel execute(UpdateNotariaCommand command);

    record UpdateNotariaCommand(
            Long id,
            String name,
            String nit,
            String direccion,
            String email,
            String categoria
    ) {}
}
