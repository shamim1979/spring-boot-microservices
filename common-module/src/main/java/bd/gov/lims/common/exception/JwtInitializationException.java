package bd.gov.lims.common.exception;

public class JwtInitializationException extends RuntimeException{
    public JwtInitializationException(String message) {
        super(message);
    }
}
