package com.notaria.domain.exception;

public class InvalidUserRoleException  extends DomainException{

    private static final String MESSAGE_INVALID = "El rol de usuario proporcionado no es válido.: '%s'.";

    private InvalidUserRoleException(final String message) {
        super(message);
    }

    public static InvalidUserRoleException becauseValueIsInvalid(final String value) {
        return new InvalidUserRoleException(String.format(MESSAGE_INVALID, value));
    }

}
