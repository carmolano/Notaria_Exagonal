package com.notaria.domain.exception;

public class InvalidNotariaNameException extends DomainException{

    private static final String MESSAGE_EMPTY = "The notaria name must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The notaria name must have at least %d characters.";

    private InvalidNotariaNameException(final String message) {
        super(message);
    }

    public static InvalidNotariaNameException becauseValueIsEmpty() {
        return new InvalidNotariaNameException(MESSAGE_EMPTY);
    }

    public static InvalidNotariaNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidNotariaNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }











}
