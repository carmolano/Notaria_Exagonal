package com.notaria.domain.desktop.cli.handler;

import com.notaria.domain.exception.UserAlreadyExistsException;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.dto.CreateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateUserHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String id       = console.readRequired("ID                              : ");
        final String name     = console.readRequired("Name                            : ");
        final String email    = console.readRequired("Email                           : ");
        final String password = console.readRequired("Password                        : ");
        final String role     = console.readRequired("Role (ADMIN / MEMBER / REVIEWER): ");

        try {
            final UserResponse created =
                    userController.createUser(new CreateUserRequest(id, name, email, password, role));
            console.println("\n  User created successfully.");
            printer.print(created);
        } catch (final UserAlreadyExistsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }

}
