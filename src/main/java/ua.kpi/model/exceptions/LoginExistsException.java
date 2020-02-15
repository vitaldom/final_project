package ua.kpi.model.exceptions;

public class LoginExistsException extends Exception {

    public LoginExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginExistsException(Throwable cause) {
        super(cause);
    }

    public LoginExistsException() {
        super();
    }

    public LoginExistsException(String string) {
        super(string);
    }

}