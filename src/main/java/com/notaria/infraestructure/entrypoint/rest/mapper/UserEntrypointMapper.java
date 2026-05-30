package com.notaria.infraestructure.entrypoint.rest.mapper;

import com.notaria.application.service.dto.command.CreateUserCommand;
import com.notaria.application.service.dto.command.DeleteUserCommand;
import com.notaria.application.service.dto.command.LoginCommand;
import com.notaria.application.service.dto.command.UpdateUserCommand;
import com.notaria.application.service.dto.query.GetUserByIdQuery;
import com.notaria.domain.model.UserModel;
import com.notaria.infrastructure.entrypoint.web.dto.CreateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.LoginRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UpdateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;

import java.util.List;

public final  class UserEntrypointMapper {
    private UserEntrypointMapper() {}

    public static CreateUserCommand toCreateCommand(final CreateUserRequest request) {
        return new CreateUserCommand(
                request.id(),
                request.name(),
                request.email(),
                request.password(),
                request.role()
        );
    }

    public static UpdateUserCommand toUpdateCommand(final UpdateUserRequest request) {
        return new UpdateUserCommand(
                request.id(),
                request.name(),
                request.email(),
                request.password(),
                request.role(),
                request.status()
        );
    }

    public static GetUserByIdQuery toGetByIdQuery(final String id) {
        return new GetUserByIdQuery(id);
    }

    public static DeleteUserCommand toDeleteCommand(final String id) {
        return new DeleteUserCommand(id);
    }

    public static LoginCommand toLoginCommand(final LoginRequest request) {
        return new LoginCommand(request.email(), request.password());
    }

    public static UserResponse toResponse(final UserModel user) {
        return new UserResponse(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getRole().name(),
                user.getStatus().name()
        );
    }

    public static List<UserResponse> toResponseList(final List<UserModel> users) {
        return users.stream().map(UserEntrypointMapper::toResponse).toList();
    }
}
