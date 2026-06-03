package com.notaria.domain.enums;

import com.notaria.domain.exception.InvalidUserStatusException;



public enum UserStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    BLOCKED;

    public static UserStatus fromString(final String value) {
        for (final UserStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw InvalidUserStatusException.becauseValueIsInvalid(value);
    }
}
