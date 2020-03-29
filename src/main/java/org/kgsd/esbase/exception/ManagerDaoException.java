package org.kgsd.esbase.exception;

public class ManagerDaoException extends ESException {
    public ManagerDaoException(String message) {
        super(message);
    }

    public ManagerDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerDaoException(Throwable cause) {
        super(cause);
    }
}
