package com.notaria.application.port.out;


import com.notaria.domain.model.NotariaModel;

public interface UpdateNotariaPort {
    NotariaModel update(NotariaModel notaria);
}
