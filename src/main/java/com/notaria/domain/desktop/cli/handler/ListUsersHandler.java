package com.notaria.domain.desktop.cli.handler;

import com.notaria.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListUsersHandler implements OperationHandler {

    private final UserController userController;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final List<UserResponse> users = userController.listAllUsers();
        printer.printList(users);
    }


}
