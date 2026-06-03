package com.notaria.domain.event;


import com.notaria.domain.model.UserModel;
import java.util.Map;
import lombok.Getter;


public class NotariaCreatedDomainEvent extends DomainEvent{

    private static final String EVENT_NAME = "notaria.created";

    private final NotariaModel notaria;

    public NotariaCreatedDomainEvent(final NotariaModel notaria) {
        super(EVENT_NAME);
        this.notaria = notaria;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", notaria.getId().value().toString(),
                "name", notaria.getName().value(),
                "nit", notaria.getNit().value(),
                "direccion", notaria.getDireccion().value(),
                "categoria", notaria.getCategoria().name());
    }
}
