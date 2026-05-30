package com.notaria.domain.desktop.cli.handler;

import com.notaria.domain.exception.InvalidCredentialsException;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.dto.LoginRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;

import lombok.RequiredArgsConstructor;
public class LoginHandler implements OperationHandler{

    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String email    = console.readRequired("Email   : ");
        final String password = console.readRequired("Password: ");
        try {
            final UserResponse user = userController.login(new LoginRequest(email, password));
            console.println("\n  Login successful. Welcome!");
            printer.print(user);
        } catch (final InvalidCredentialsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }

}
