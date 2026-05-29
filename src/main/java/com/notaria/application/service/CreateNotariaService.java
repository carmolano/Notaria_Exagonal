package com.notaria.application.service;

import com.notaria.application.in.CreateNotariaUseCase;
import com.notaria.application.out.GetNotariaByEmailPort;
import com.notaria.application.out.GetNotariaByNitPort;
import com.notaria.application.out.SaveNotariaPort;
import com.notaria.application.service.dto.command.CreateNotariaCommand;
import com.notaria.application.service.mapper.NotariaApplicationMapper;
import com.notaria.domain.exception.NotariaAlreadyExistsException;
import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaEmail;
import com.notaria.domain.valueobject.NotariaNit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNotariaService implements CreateNotariaUseCase {

    private final SaveNotariaPort saveNotariaPort;
    private final GetNotariaByNitPort getNotariaByNitPort;
    private final GetNotariaByEmailPort getNotariaByEmailPort;
    private final Validator validator;

    @Override
    public NotariaModel execute(final CreateNotariaCommand command) {
        validateCommand(command);

        final NotariaNit nit = new NotariaNit(command.nit());
        final NotariaEmail email = new NotariaEmail(command.email());

        ensureNitIsNotTaken(nit);
        ensureEmailIsNotTaken(email);

        final NotariaModel notariaToSave = NotariaApplicationMapper.fromCreateCommandToModel(command);
        return saveNotariaPort.save(notariaToSave);
    }

    private void validateCommand(final CreateNotariaCommand command) {
        final Set<ConstraintViolation<CreateNotariaCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureNitIsNotTaken(final NotariaNit nit) {
        getNotariaByNitPort
                .getByNit(nit)
                .ifPresent(ignored -> {
                    throw NotariaAlreadyExistsException.becauseNitAlreadyExists(nit.value());
                });
    }

    private void ensureEmailIsNotTaken(final NotariaEmail email) {
        getNotariaByEmailPort
                .getByEmail(email)
                .ifPresent(ignored -> {
                    throw NotariaAlreadyExistsException.becauseEmailAlreadyExists(email.value());
                });
    }
}