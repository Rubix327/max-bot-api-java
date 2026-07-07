package ru.max.botapi.client;

import java.io.Serial;

public class MaxSslInitializationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MaxSslInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

}
