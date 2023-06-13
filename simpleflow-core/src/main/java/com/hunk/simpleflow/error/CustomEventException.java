package com.hunk.simpleflow.error;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>
 */
public class CustomEventException extends RuntimeException {

    public CustomEventException() {}

    public CustomEventException(String message) {
        super(message);
    }

    public CustomEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEventException(Throwable cause) {
        super(cause);
    }

    protected CustomEventException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
