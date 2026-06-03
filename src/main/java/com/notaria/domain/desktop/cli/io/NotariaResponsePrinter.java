package com.notaria.domain.desktop.cli.io;
import com.notaria.infrastructure.entrypoint.web.dto.NotariaResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotariaResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-10s : %s%n";

    private final ConsoleIO console;

    public void print(final NotariaResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "ID",        response.id());
        console.printf(ROW_FORMAT, "Name",      response.name());
        console.printf(ROW_FORMAT, "NIT",       response.nit());
        console.printf(ROW_FORMAT, "Dirección", response.direccion());
        console.printf(ROW_FORMAT, "Email",     response.email());
        console.printf(ROW_FORMAT, "Categoría", response.categoria());
        console.println(SEPARATOR);
    }

    public void printList(final List<NotariaResponse> notarias) {
        if (notarias.isEmpty()) {
            console.println("  No notarias found.");
            return;
        }
        console.printf("%n  Total: %d notaria(s)%n", notarias.size());
        notarias.forEach(this::print);
    }

}
