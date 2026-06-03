package com.notaria.application.service;

import com.notaria.application.in.DeleteNotariaUseCase;
import com.notaria.application.out.DeleteNotariaPort;
import com.notaria.application.out.GetNotariaByIdPort;
import com.notaria.application.service.dto.command.DeleteNotariaCommand;
import com.notaria.application.service.mapper.NotariaApplicationMapper;
import com.notaria.domain.exception.NotariaNotFoundException;
import com.notaria.domain.valueobject.NotariaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNotariaService implements DeleteNotariaUseCase {

    private final DeleteNotariaPort deleteNotariaPort;
    private final GetNotariaByIdPort getNotariaByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteNotariaCommand command) {
        validateCommand(command);

        final NotariaId notariaId = NotariaApplicationMapper.fromDeleteCommandToNotariaId(command);
        ensureNotariaExists(notariaId);
        deleteNotariaPort.delete(notariaId);
    }

    private void validateCommand(final DeleteNotariaCommand command) {
        final Set<ConstraintViolation<DeleteNotariaCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureNotariaExists(final NotariaId notariaId) {
        getNotariaByIdPort
                .getById(notariaId)
                .orElseThrow(() -> NotariaNotFoundException.becauseIdWasNotFound(notariaId.value()));
    }

}
