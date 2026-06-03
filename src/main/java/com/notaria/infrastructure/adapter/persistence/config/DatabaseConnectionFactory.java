package com.notaria.infraestructure.adapter.persistence.config;

import com.notaria.infrastructure.adapter.persistence.exception.UserPersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;



@UtilityClass
public class DatabaseConnectionFactory {
    public Connection createConnection(final DatabaseConfig config) {
        try {
            return DriverManager.getConnection(
                    config.buildJdbcUrl(), config.username(), config.password());
        } catch (final SQLException exception) {
            // Nota: Se utiliza UserPersistenceException o NotariaPersistenceException según corresponda.
            // Al ser un factory generalizado, se puede lanzar la excepción base de infraestructura seleccionada.
            throw UserPersistenceException.becauseConnectionFailed(exception);
        }
    }
}
