package com.notaria.infraestructure.adapter.persistence.mapper;


import com.notaria.domain.enums.NotariaCategoria;
import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaDireccion;
import com.notaria.domain.valueobject.NotariaEmail;
import com.notaria.domain.valueobject.NotariaId;
import com.notaria.domain.valueobject.NotariaName;
import com.notaria.domain.valueobject.NotariaNit;
import com.notaria.infrastructure.adapter.persistence.entity.NotariaEntity;

public final class NotariaPersistenceMapper {

    public static NotariaEntity toEntity(final NotariaModel model) {
        return new NotariaEntity(
                model.getId().value(),
                model.getName().value(),
                model.getNit().value(),
                model.getDireccion().value(),
                model.getEmail().value(),
                model.getCategoria().name()
        );
    }

    public static NotariaModel toModel(final NotariaEntity entity) {
        return new NotariaModel(
                new NotariaId(entity.getId()),
                new NotariaName(entity.getName()),
                new NotariaNit(entity.getNit()),
                new NotariaDireccion(entity.getDireccion()),
                new NotariaEmail(entity.getEmail()),
                NotariaCategoria.fromString(entity.getCategoria())
        );
    }
}
