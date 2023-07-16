package bd.gov.lims.base.exception;

public class JwtInitializationException extends RuntimeException{
    public JwtInitializationException(String message) {
        super(message);
    }
}
