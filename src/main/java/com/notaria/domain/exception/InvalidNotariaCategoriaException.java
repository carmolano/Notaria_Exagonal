package com.notaria.domain.exception;

public class InvalidNotariaCategoriaException  extends DomainException{

    private static final String MESSAGE_INVALID = "The notaria category provided is invalid: '%s'.";

    private InvalidNotariaCategoriaException(final String message) {
        super(message);
    }

    public static InvalidNotariaCategoriaException becauseValueIsInvalid(final String value) {
        return new InvalidNotariaCategoriaException(String.format(MESSAGE_INVALID, value));
    }

}
