package com.notaria.infraestructure.config;


import com.notaria.application.port.in.*;
import com.notaria.application.service.*;
import com.notaria.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.notaria.infrastructure.adapter.email.SmtpConfig;
import com.notaria.infrastructure.adapter.persistence.NotariaPersistenceAdapter;
import com.notaria.infrastructure.adapter.persistence.UserPersistenceAdapter;
import com.notaria.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.notaria.infrastructure.adapter.persistence.repository.SpringDataNotariaRepository;
import com.notaria.infrastructure.adapter.persistence.repository.SpringDataUserRepository;
import com.notaria.infrastructure.entrypoint.web.UserController;
import com.notaria.infrastructure.entrypoint.web.NotariaController;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.Validator;
import javax.sql.DataSource;

public class DependencyContainer {



    private static final String DB_HOST     = "db.host";
    private static final String DB_PORT     = "db.port";
    private static final String DB_NAME     = "db.name";
    private static final String DB_USER     = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static final String SMTP_HOST      = "smtp.host";
    private static final String SMTP_PORT      = "smtp.port";
    private static final String SMTP_USER      = "smtp.username";
    private static final String SMTP_PASSWORD  = "smtp.password";
    private static final String SMTP_FROM      = "smtp.from.address";
    private static final String SMTP_FROM_NAME = "smtp.from.name";

    private final UserController userController;
    private final NotariaController notariaController;

    public DependencyContainer(
            final SpringDataUserRepository springUserRepository,
            final SpringDataNotariaRepository springNotariaRepository) {

        final AppProperties properties = new AppProperties();
        final Validator validator = ValidatorProvider.buildValidator();

        // 1. Inicialización de Adaptadores de Salida (Outbound Adapters)
        final UserPersistenceAdapter userPersistenceAdapter = new UserPersistenceAdapter(springUserRepository);
        final NotariaPersistenceAdapter notariaPersistenceAdapter = new NotariaPersistenceAdapter(springNotariaRepository);

        final SmtpConfig smtpConfig = buildSmtpConfig(properties);
        final JavaMailEmailSenderAdapter emailSenderAdapter = new JavaMailEmailSenderAdapter(smtpConfig);

        // 2. Servicios de Aplicación Internos
        final EmailNotificationService emailNotificationService = new EmailNotificationService(emailSenderAdapter);

        // 3. Inicialización de Casos de Uso (Use Cases / Input Ports) - Usuarios
        final CreateUserUseCase createUserUseCase = new CreateUserService(userPersistenceAdapter, userPersistenceAdapter, emailNotificationService, validator);
        final UpdateUserUseCase updateUserUseCase = new UpdateUserService(userPersistenceAdapter, userPersistenceAdapter, userPersistenceAdapter, emailNotificationService, validator);
        final DeleteUserUseCase deleteUserUseCase = new DeleteUserService(userPersistenceAdapter, userPersistenceAdapter, validator);
        final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userPersistenceAdapter, validator);
        final GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersService(userPersistenceAdapter);
        final LoginUseCase loginUseCase = new LoginService(userPersistenceAdapter, validator);

        // 4. Inicialización de Casos de Uso - Notarías
        final CreateNotariaUseCase createNotariaUseCase = new CreateNotariaService(notariaPersistenceAdapter, notariaPersistenceAdapter, notariaPersistenceAdapter, validator);
        final UpdateNotariaUseCase updateNotariaUseCase = new UpdateNotariaService(notariaPersistenceAdapter, notariaPersistenceAdapter, notariaPersistenceAdapter, validator);
        final DeleteNotariaUseCase deleteNotariaUseCase = new DeleteNotariaService(notariaPersistenceAdapter, notariaPersistenceAdapter, validator);
        final GetNotariaByIdUseCase getNotariaByIdUseCase = new GetNotariaByIdService(notariaPersistenceAdapter, validator);
        final GetAllNotariasUseCase getAllNotariasUseCase = new GetAllNotariasService(notariaPersistenceAdapter);

        // 5. Acoplamiento a Controladores de Entrada (Inbound Adapters / Entrypoints)
        this.userController = new UserController(
                createUserUseCase,
                updateUserUseCase,
                deleteUserUseCase,
                getUserByIdUseCase,
                getAllUsersUseCase,
                loginUseCase
        );

        this.notariaController = new NotariaController(
                createNotariaUseCase,
                updateNotariaUseCase,
                deleteNotariaUseCase,
                getNotariaByIdUseCase,
                getAllNotariasUseCase
        );
    }

    public UserController userController() {
        return userController;
    }

    public NotariaController notariaController() {
        return notariaController;
    }

    private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
        return new SmtpConfig(
                properties.get(SMTP_HOST),
                properties.getInt(SMTP_PORT),
                properties.get(SMTP_USER),
                properties.get(SMTP_PASSWORD),
                properties.get(SMTP_FROM),
                properties.get(SMTP_FROM_NAME)
        );
    }
}
