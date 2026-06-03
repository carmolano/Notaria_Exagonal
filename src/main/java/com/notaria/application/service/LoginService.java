package com.notaria.application.service;

import com.notaria.application.port.in.LoginUseCase;
import com.notaria.application.port.out.GetUserByEmailPort;
import com.notaria.application.service.dto.command.LoginCommand;
import com.notaria.domain.enums.UserStatus;
import com.notaria.domain.exception.InvalidCredentialsException;
import com.notaria.domain.model.UserModel;
import com.notaria.domain.valueobject.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final GetUserByEmailPort getUserByEmailPort;
    private final Validator validator;

    @Override
    public UserModel execute(final LoginCommand command) {
        validateCommand(command);

        final UserEmail email = new UserEmail(command.email());
        final UserModel user = findUserOrFailWithInvalidCredentials(email);

        verifyPasswordOrFail(command.password(), user);
        ensureUserIsActiveOrFail(user);

        return user;
    }

    private void validateCommand(final LoginCommand command) {
        final Set<ConstraintViolation<LoginCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private UserModel findUserOrFailWithInvalidCredentials(final UserEmail email) {
        return getUserByEmailPort
                .getByEmail(email)
                .orElseThrow(InvalidCredentialsException::becauseCredentialsAreInvalid);
    }

    private static void verifyPasswordOrFail(final String plainPassword, final UserModel user) {
        if (!user.getPassword().verifyPlain(plainPassword)) {
            throw InvalidCredentialsException.becauseCredentialsAreInvalid();
        }
    }

    private static void ensureUserIsActiveOrFail(final UserModel user) {
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw InvalidCredentialsException.becauseUserIsNotActive();
        }
    }
}
