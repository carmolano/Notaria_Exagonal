package com.notaria.infraestructure.adapter.persistence.exception;
public final class NotariaPersistenceException {

    private static final String MESSAGE_SAVE = "Failed to save notaria with ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Failed to update notaria with ID: '%s'.";
    private static final String MESSAGE_FIND_ID = "Failed to find notaria with ID: '%s'.";
    private static final String MESSAGE_FIND_NIT = "Failed to find notaria with NIT: '%s'.";
    private static final String MESSAGE_FIND_EMAIL = "Failed to find notaria with email: '%s'.";
    private static final String MESSAGE_ALL = "Failed to retrieve all notarias.";
    private static final String MESSAGE_DELETE = "Failed to delete notaria with ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

    private NotariaPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static NotariaPersistenceException becauseSaveFailed(final String notariaId, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_SAVE, notariaId), cause);
    }

    public static NotariaPersistenceException becauseUpdateFailed(final String notariaId, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_UPDATE, notariaId), cause);
    }

    public static NotariaPersistenceException becauseFindByIdFailed(final String notariaId, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_FIND_ID, notariaId), cause);
    }

    public static NotariaPersistenceException becauseFindByNitFailed(final String nit, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_FIND_NIT, nit), cause);
    }

    public static NotariaPersistenceException becauseFindByEmailFailed(final String email, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_FIND_EMAIL, email), cause);
    }

    public static NotariaPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new NotariaPersistenceException(MESSAGE_ALL, cause);
    }

    public static NotariaPersistenceException becauseDeleteFailed(final String notariaId, final Throwable cause) {
        return new NotariaPersistenceException(String.format(MESSAGE_DELETE, notariaId), cause);
    }

    public static NotariaPersistenceException becauseConnectionFailed(final Throwable cause) {
        return new NotariaPersistenceException(MESSAGE_CONNECTION, cause);
    }


}
