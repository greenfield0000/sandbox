package greenfield.group.com.menuservice.security.exceptions;

public class JwtAuthenticationException extends Exception {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
