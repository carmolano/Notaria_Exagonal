package com.notaria.domain.valueobject;

import com.notaria.domain.exception.InvalidNotariaEmailException;
import java.util.Objects;
import java.util.regex.Pattern;


public record record NotariaEmail {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public NotariaEmail {
        final String normalizedValue =
                Objects.requireNonNull(value, "NotariaEmail no puede ser nula").trim().toLowerCase();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidNotariaEmailException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String normalizedValue) {
        if (!EMAIL_PATTERN.matcher(normalizedValue).matches()) {
            throw InvalidNotariaEmailException.becauseFormatIsInvalid(normalizedValue);
        }
    }

    @Override
    public String toString() {
        return value;
    }




}
