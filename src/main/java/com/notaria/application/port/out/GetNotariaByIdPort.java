package com.notaria.application.port.out;


import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaId;
import java.util.Optional;

public interface GetNotariaByIdPort {
    Optional<NotariaModel> getById(NotariaId notariaId);
}
