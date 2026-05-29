package com.notaria.application.service.mapper;

import com.notaria.application.service.dto.command.CreateNotariaCommand;
import com.notaria.application.service.dto.command.UpdateNotariaCommand;
import com.notaria.application.service.dto.command.DeleteNotariaCommand;
import com.notaria.application.service.dto.query.GetNotariaByIdQuery;
import com.notaria.domain.enums.NotariaCategoria;
import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaDireccion;
import com.notaria.domain.valueobject.NotariaEmail;
import com.notaria.domain.valueobject.NotariaId;
import com.notaria.domain.valueobject.NotariaName;
import com.notaria.domain.valueobject.NotariaNit;

public class NotariaApplicationMapper {

    private NotariaApplicationMapper() {}

    public static NotariaModel fromCreateCommandToModel(final CreateNotariaCommand command) {
        return NotariaModel.create(
                new NotariaId(null), // El ID se auto-genera comúnmente en base de datos para tipos Long
                new NotariaName(command.name()),
                new NotariaNit(command.nit()),
                new NotariaDireccion(command.direccion()),
                new NotariaEmail(command.email()),
                NotariaCategoria.fromString(command.categoria())
        );
    }

    public static NotariaModel fromUpdateCommandToModel(final UpdateNotariaCommand command) {
        return NotariaModel.create(
                new NotariaId(command.id()),
                new NotariaName(command.name()),
                new NotariaNit(command.nit()),
                new NotariaDireccion(command.direccion()),
                new NotariaEmail(command.email()),
                NotariaCategoria.fromString(command.categoria())
        );
    }

    public static NotariaId fromDeleteCommandToNotariaId(final DeleteNotariaCommand command) {
        return new NotariaId(command.id());
    }

    public static NotariaId fromGetNotariaByIdQueryToNotariaId(final GetNotariaByIdQuery query) {
        return new NotariaId(query.id());
    }
}
