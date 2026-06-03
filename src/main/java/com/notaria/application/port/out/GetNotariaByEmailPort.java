package com.notaria.application.port.out;
import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaEmail;

import java.util.Optional;

public interface GetNotariaByEmailPort {
    Optional<NotariaModel> getByEmail(NotariaEmail email);
}
