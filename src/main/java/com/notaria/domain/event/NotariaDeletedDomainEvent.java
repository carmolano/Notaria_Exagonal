package com.notaria.domain.event;

import com.notaria.domain.valueobject.NotariaId;
import java.util.Map;
import lombok.Getter;


public final  class NotariaDeletedDomainEvent extends DomainEvent{

    private static final String EVENT_NAME = "notaria.deleted";

    private final NotariaId notariaId;

    public NotariaDeletedDomainEvent(final NotariaId notariaId) {
        super(EVENT_NAME);
        this.notariaId = notariaId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", notariaId.value().toString());
    }

}
