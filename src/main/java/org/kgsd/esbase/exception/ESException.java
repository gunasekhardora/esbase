package org.kgsd.esbase.exception;

public class ESException extends Exception {
    public ESException(String message) {
        super(message);
    }

    public ESException(String message, Throwable cause) {
        super(message, cause);
    }

    public ESException(Throwable cause) {
        super(cause);
    }
}
