package com.notaria.application.port.in;

import com.notaria.domain.model.UserModel;


public interface CreateNotariaUseCase {
    UserModel execute (CreateNotariaCommand command);

    record CreateNotariaCommand(
            String name,
            String nit,
            String direccion,
            String email,
            String categoria
    ) {}

}
