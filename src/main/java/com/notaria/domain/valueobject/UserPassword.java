package com.notaria.domain.valueobject;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.notaria.domain.exception.InvalidUserPasswordException;
import java.util.Objects;


public final class UserPassword {
    private static final int MINIMUM_LENGTH = 8;
    private static final int BCRYPT_COST = 12;

    private final String value;

    private UserPassword(final String value) {
        this.value = value;
    }


    public static UserPassword fromPlainText(final String plainText) {
        final String normalizedValue =
                Objects.requireNonNull(plainText, "La contraseña no puede ser nula").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        final String hash =
                BCrypt.withDefaults().hashToString(BCRYPT_COST, normalizedValue.toCharArray());
        return new UserPassword(hash);
    }


    public static UserPassword fromHash(final String hash) {
        Objects.requireNonNull(hash, "La contraseña no puede ser nula");
        return new UserPassword(hash);
    }


    public boolean verifyPlain(final String plainText) {
        final String normalizedPlain =
                Objects.requireNonNull(plainText, "La contraseña simple no puede ser nula.").trim();
        final BCrypt.Result result = BCrypt.verifyer().verify(normalizedPlain.toCharArray(), value);
        return result.verified;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (!(other instanceof UserPassword userPassword)) return false;
        return Objects.equals(value, userPassword.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidUserPasswordException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidUserPasswordException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }





























}
