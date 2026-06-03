package com.notaria.application.port.in;

import com.notaria.application.service.dto.query.GetNotariaByIdQuery;
import com.notaria.domain.model.NotariaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface  GetNotariaByIdUseCase {
    NotariaModel execute(@NotNull @Valid GetNotariaByIdQuery query);
}
