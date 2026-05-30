package com.notaria.domain.desktop.cli.handler;

import com.notaria.domain.exception.UserNotFoundException;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserByIdHandler implements OperationHandler{

    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("User ID: ");
        try {
            final UserResponse user = userController.findUserById(id);
            printer.print(user);
        } catch (final UserNotFoundException exception) {
            console.println("  Not found: " + exception.getMessage());
        }
    }



}
