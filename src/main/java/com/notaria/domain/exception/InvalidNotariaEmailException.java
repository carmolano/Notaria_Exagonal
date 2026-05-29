package com.notaria.domain.exception;

public final class InvalidNotariaEmailException extends DomainException {

    private static final String MESSAGE_EMPTY = "The notaria email must not be empty.";
    private static final String MESSAGE_INVALID_FORMAT = "The notaria email format is invalid: '%s'.";

    private InvalidNotariaEmailException(final String message) {
        super(message);
    }

    public static InvalidNotariaEmailException becauseValueIsEmpty() {
        return new InvalidNotariaEmailException(MESSAGE_EMPTY);
    }

    public static InvalidNotariaEmailException becauseFormatIsInvalid(final String email) {
        return new InvalidNotariaEmailException(String.format(MESSAGE_INVALID_FORMAT, email));
    }
}