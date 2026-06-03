package com.notaria.application.port.out;
import com.notaria.domain.valueobject.NotariaId;

public interface DeleteNotariaPort {
    void delete (NotariaId notariaId);
}
