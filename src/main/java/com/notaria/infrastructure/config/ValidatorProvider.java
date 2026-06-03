package com.notaria.infraestructure.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class ValidatorProvider {
    private ValidatorProvider() {}

    public static Validator buildValidator() {
        try (final ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()) {
            return factory.getValidator();
        }
    }
}
