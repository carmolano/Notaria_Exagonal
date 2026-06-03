package com.notaria.application.service;

import com.notaria.application.in.UpdateNotariaUseCase;
import com.notaria.application.out.GetNotariaByEmailPort;
import com.notaria.application.out.GetNotariaByIdPort;
import com.notaria.application.out.GetNotariaByNitPort;
import com.notaria.application.out.UpdateNotariaPort;
import com.notaria.application.service.dto.command.UpdateNotariaCommand;
import com.notaria.application.service.mapper.NotariaApplicationMapper;
import com.notaria.domain.exception.NotariaAlreadyExistsException;
import com.notaria.domain.exception.NotariaNotFoundException;
import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaEmail;
import com.notaria.domain.valueobject.NotariaId;
import com.notaria.domain.valueobject.NotariaNit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateNotariaService implements UpdateNotariaUseCase {

    private final UpdateNotariaPort updateNotariaPort;
    private final GetNotariaByIdPort getNotariaByIdPort;
    private final GetNotariaByNitPort getNotariaByNitPort;
    private final GetNotariaByEmailPort getNotariaByEmailPort;
    private final Validator validator;

    @Override
    public NotariaModel execute(final UpdateNotariaCommand command) {
        validateCommand(command);

        final NotariaId notariaId = new NotariaId(command.id());
        ensureNotariaExists(notariaId);

        final NotariaNit newNit = new NotariaNit(command.nit());
        final NotariaEmail newEmail = new NotariaEmail(command.email());

        ensureNitIsNotTakenByAnotherNotaria(newNit, notariaId);
        ensureEmailIsNotTakenByAnotherNotaria(newEmail, notariaId);

        final NotariaModel notariaToUpdate = NotariaApplicationMapper.fromUpdateCommandToModel(command);
        return updateNotariaPort.update(notariaToUpdate);
    }

    private void validateCommand(final UpdateNotariaCommand command) {
        final Set<ConstraintViolation<UpdateNotariaCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureNotariaExists(final NotariaId notariaId) {
        getNotariaByIdPort
                .getById(notariaId)
                .orElseThrow(() -> NotariaNotFoundException.becauseIdWasNotFound(notariaId.value()));
    }

    private void ensureNitIsNotTakenByAnotherNotaria(final NotariaNit newNit, final NotariaId ownerId) {
        getNotariaByNitPort
                .getByNit(newNit)
                .ifPresent(found -> {
                    if (!found.getId().equals(ownerId)) {
                        throw NotariaAlreadyExistsException.becauseNitAlreadyExists(newNit.value());
                    }
                });
    }

    private void ensureEmailIsNotTakenByAnotherNotaria(final NotariaEmail newEmail, final NotariaId ownerId) {
        getNotariaByEmailPort
                .getByEmail(newEmail)
                .ifPresent(found -> {
                    if (!found.getId().equals(ownerId)) {
                        throw NotariaAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
                    }
                });
    }
}