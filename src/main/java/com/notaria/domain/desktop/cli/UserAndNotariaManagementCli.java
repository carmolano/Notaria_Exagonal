package com.notaria.domain.desktop.cli;
import com.notaria.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.notaria.infrastructure.entrypoint.desktop.cli.io.ResponsePrinter;
import com.notaria.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.NotariaController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserAndNotariaManagementCli {
    private static final String BANNER =
            """
            ==========================================
                 Notaria & Users Management System
            ===========================================""";

    private static final String MENU_BORDER = "  ==========================================";

    private final UserController userController;
    private final NotariaController notariaController;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final ResponsePrinter printer = new ResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Option: ");
            final Optional<MenuOption> option = MenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Invalid option. Please try again.");
            } else if (option.get() == MenuOption.EXIT) {
                console.println("\n  Goodbye!\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
        try {
            final OperationHandler handler = handlers.get(option);
            if (handler != null) {
                handler.handle();
            } else {
                console.println("  Operation not implemented yet.");
            }
        } catch (final ConstraintViolationException exception) {
            console.println("  Validation errors:");
            exception.getConstraintViolations()
                    .forEach(violation -> console.println("    - " + violation.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Unexpected error: " + exception.getMessage());
        }
    }

    private Map<MenuOption, OperationHandler> buildHandlers(final ResponsePrinter printer) {
        // Aquí registrarás todos los handlers tanto de usuarios como de notarías
        // pasándole sus respectivos controladores.
        return Map.of(
                // Ejemplo de mapeo modular que usará tus sub-handlers:
                // MenuOption.LIST_USERS,       new ListUsersHandler(userController, printer),
                // MenuOption.LIST_NOTARIAS,    new ListNotariasHandler(notariaController, printer)
        );
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Main Menu");
        console.println(MENU_BORDER);
        for (final MenuOption option : MenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }
}
