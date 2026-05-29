package com.notaria.domain.exception;

public class NotariaNotFoundException extends DomainException{
    private static final String MESSAGE_NOT_FOUND = "La usuario con ID% d no fue encontrada.";

    private NotariaNotFoundException(final String message) {
        super(message);
    }

    public static NotariaNotFoundException becauseIdDoesNotExist(final Long id) {
        return new NotariaNotFoundException(String.format(MESSAGE_NOT_FOUND, id));
    }
}
