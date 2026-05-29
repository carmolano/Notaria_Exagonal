package com.notaria.domain.enums;

import com.notaria.domain.exception.InvalidNotariaCategoriaException;

public enum NotariaCategoria {

    PRIMERA,
    SEGUNDA,
    TERCERA;

    public static NotariaCategoria fromString(final String value) {
        for (final NotariaCategoria categoria : values()) {
            if (categoria.name().equalsIgnoreCase(value)) {
                return categoria;
            }
        }
        throw InvalidNotariaCategoriaException.becauseValueIsInvalid(value);
    }





}
