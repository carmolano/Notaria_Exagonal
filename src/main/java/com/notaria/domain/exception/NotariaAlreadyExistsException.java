package com.notaria.domain.exception;

public class NotariaAlreadyExistsException extends DomainException{

    private static final String MESSAGE_EXISTS = "La notaria con NIT '%s' ya existe..";

    private NotariaAlreadyExistsException(final String message) {
        super(message);
    }

    public static final NotariaAlreadyExistsException becauseNitIsAlreadyRegistered(final String nit) {
        return new NotariaAlreadyExistsException(String.format(MESSAGE_EXISTS, nit));
    }

}
