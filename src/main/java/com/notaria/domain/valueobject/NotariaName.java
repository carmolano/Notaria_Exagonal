package com.notaria.domain.valueobject;


import com.notaria.domain.exception.InvalidNotariaNameException;
import java.util.Objects;

public record NotariaName (String value){

    public NotariaName {
        final String normalizedValue = Objects.requireNonNull(value, "NotariaName no puede ser nula").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidNotariaNameException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidNotariaNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }






}
