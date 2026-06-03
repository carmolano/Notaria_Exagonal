package com.notaria.domain.valueobject;

import com.notaria.domain.exception.InvalidNotariaIdException;
import java.util.Objects;


public record NotariaId (Long value){

    public NotariaId {
        Objects.requireNonNull(value, "NotariaId cannot be null");
        validateNotEmpty(value);
    }

    private static void validateNotEmpty(final Long value) {
        if (value <= 0) {
            throw InvalidNotariaIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }






}
