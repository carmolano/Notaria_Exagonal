package com.notaria.domain.exception;

public class InvalidUserPasswordException extends DomainException{
    private static final String MESSAGE_EMPTY = "La contraseña del usuario no debe estar vacía..";
    private static final String MESSAGE_TOO_SHORT = "La contraseña del usuario debe tener al menos %d caracteres..";

    private InvalidUserPasswordException(final String message) {
        super(message);
    }

    public static InvalidUserPasswordException becauseValueIsEmpty() {
        return new InvalidUserPasswordException(MESSAGE_EMPTY);
    }

    public static InvalidUserPasswordException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidUserPasswordException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
