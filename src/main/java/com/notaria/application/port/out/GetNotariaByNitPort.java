package com.notaria.application.port.out;



import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaNit;
import java.util.Optional;

public interface GetNotariaByNitPort {
    Optional<NotariaModel> getByNit
}
