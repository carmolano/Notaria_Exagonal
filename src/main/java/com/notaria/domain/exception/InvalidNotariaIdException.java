package com.notaria.domain.exception;

public class InvalidNotariaIdException  extends DomainException{

    private static final String MESSAGE_EMPTY = "The notaria id must not be empty.";

    private InvalidNotariaIdException(final String message) {
        super(message);
    }

    public static InvalidNotariaIdException becauseValueIsEmpty() {
        return new InvalidNotariaIdException(MESSAGE_EMPTY);
    }

}
