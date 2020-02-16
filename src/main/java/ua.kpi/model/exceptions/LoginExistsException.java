package ua.kpi.model.exceptions;

public class LoginExistsException extends Exception {

    public LoginExistsException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public LoginExistsException(Throwable cause) {
        super(cause);
    }

    public LoginExistsException() {
        super();
    }

    public LoginExistsException(String errorMessage) {
        super(errorMessage);
    }

}