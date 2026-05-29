package com.notaria.domain.exception;

public class InvalidUserStatusException  extends DomainException{

    private static final String MESSAGE_INVALID = "The user status provided is invalid: '%s'.";

    private InvalidUserStatusException(final String message) {
        super(message);
    }

    public static InvalidUserStatusException becauseValueIsInvalid(final String value) {
        return new InvalidUserStatusException(String.format(MESSAGE_INVALID, value));
    }

}
