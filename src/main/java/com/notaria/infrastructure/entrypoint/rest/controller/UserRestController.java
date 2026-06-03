package com.notaria.infraestructure.entrypoint.rest.controller;

import com.notaria.application.port.in.CreateUserUseCase;
import com.notaria.application.port.in.DeleteUserUseCase;
import com.notaria.application.port.in.GetAllUsersUseCase;
import com.notaria.application.port.in.GetUserByIdUseCase;
import com.notaria.application.port.in.UpdateUserUseCase;
import com.notaria.application.service.dto.command.CreateUserCommand;
import com.notaria.application.service.dto.command.DeleteUserCommand;
import com.notaria.application.service.dto.command.UpdateUserCommand;
import com.notaria.application.service.dto.query.GetUserByIdQuery;
import com.notaria.domain.model.UserModel;
import com.notaria.infrastructure.entrypoint.web.dto.CreateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UpdateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import com.notaria.infrastructure.entrypoint.web.mapper.UserEntrypointMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserRestController implements UserRestControllerDocs{

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody final CreateUserRequest request) {
        final CreateUserCommand command = UserEntrypointMapper.toCreateCommand(request);
        final UserModel user = createUserUseCase.execute(command);
        return UserEntrypointMapper.toResponse(user);
    }

    @Override
    @GetMapping
    public List<UserResponse> getAll() {
        return UserEntrypointMapper.toResponseList(getAllUsersUseCase.execute());
    }

    @Override
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable final String id) {
        final GetUserByIdQuery query = UserEntrypointMapper.toGetByIdQuery(id);
        final UserModel user = getUserByIdUseCase.execute(query);
        return UserEntrypointMapper.toResponse(user);
    }

    @Override
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable final String id,
            @Valid @RequestBody final UpdateUserRequest request) {

        // Garantizamos consistencia inyectando el ID de la URL en la estructura del comando
        final UpdateUserRequest consolidatedRequest = new UpdateUserRequest(
                id,
                request.name(),
                request.email(),
                request.password(),
                request.role(),
                request.status()
        );

        final UpdateUserCommand command = UserEntrypointMapper.toUpdateCommand(consolidatedRequest);
        final UserModel user = updateUserUseCase.execute(command);
        return UserEntrypointMapper.toResponse(user);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        final DeleteUserCommand command = UserEntrypointMapper.toDeleteCommand(id);
        deleteUserUseCase.execute(command);
    }
}
