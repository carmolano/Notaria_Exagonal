package com.notaria.application.service;

import com.notaria.application.in.GetUserByIdUseCase;
import com.notaria.application.out.GetUserByIdPort;
import com.notaria.application.service.dto.query.GetUserByIdQuery;
import com.notaria.application.service.mapper.UserApplicationMapper;
import com.notaria.domain.exception.UserNotFoundException;
import com.notaria.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNotariaByIdService implements GetNotariaByIdUseCase{
    private final GetNotariaByIdPort getNotariaByIdPort;
    private final Validator validator;

    @Override
    public NotariaModel execute(final GetNotariaByIdQuery query) {
        validateQuery(query);

        final NotariaId notariaId = NotariaApplicationMapper.fromGetNotariaByIdQueryToNotariaId(query);
        return getNotariaByIdPort
                .getById(notariaId)
                .orElseThrow(() -> NotariaNotFoundException.becauseIdWasNotFound(notariaId.value()));
    }

    private void validateQuery(final GetNotariaByIdQuery query) {
        final Set<ConstraintViolation<GetNotariaByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
