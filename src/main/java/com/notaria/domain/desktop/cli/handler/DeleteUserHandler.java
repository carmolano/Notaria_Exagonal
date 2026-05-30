package com.notaria.domain.desktop.cli.handler;
import com.notaria.domain.exception.UserNotFoundException;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.notaria.infrastructure.entrypoint.web.UserController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserHandler implements OperationHandler{

    private final UserController userController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String id = console.readRequired("User ID to delete: ");
        try {
            userController.deleteUser(id);
            console.println("  User deleted successfully.");
        } catch (final UserNotFoundException exception) {
            console.println("  Not found: " + exception.getMessage());
        }
    }

}
