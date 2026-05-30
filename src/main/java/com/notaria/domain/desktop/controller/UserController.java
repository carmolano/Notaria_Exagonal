package com.notaria.domain.desktop.controller;


import com.notaria.application.port.in.*;
import com.notaria.infrastructure.entrypoint.web.dto.CreateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.LoginRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UpdateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import com.notaria.infrastructure.entrypoint.web.mapper.UserEntrypointMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public final class  UserController {


    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final LoginUseCase loginUseCase;

    public List<UserResponse> listAllUsers() {
        final var users = getAllUsersUseCase.execute();
        return UserEntrypointMapper.toResponseList(users);
    }

    public UserResponse findUserById(final String id) {
        final var query = UserEntrypointMapper.toGetByIdQuery(id);
        final var user = getUserByIdUseCase.execute(query);
        return UserEntrypointMapper.toResponse(user);
    }

    public UserResponse createUser(final CreateUserRequest request) {
        final var command = UserEntrypointMapper.toCreateCommand(request);
        final var user = createUserUseCase.execute(command);
        return UserEntrypointMapper.toResponse(user);
    }

    public UserResponse updateUser(final UpdateUserRequest request) {
        final var command = UserEntrypointMapper.toUpdateCommand(request);
        final var user = updateUserUseCase.execute(command);
        return UserEntrypointMapper.toResponse(user);
    }

    public void deleteUser(final String id) {
        final var command = UserEntrypointMapper.toDeleteCommand(id);
        deleteUserUseCase.execute(command);
    }

    public UserResponse login(final LoginRequest request) {
        final var command = UserEntrypointMapper.toLoginCommand(request);
        final var user = loginUseCase.execute(command);
        return UserEntrypointMapper.toResponse(user);
    }
}
