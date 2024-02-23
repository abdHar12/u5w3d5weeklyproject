package harouane.u5w3d5weeklyproject.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{

    List<ObjectError> errors;
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errors) {
        super("Errori riportati di seguito");
        this.errors = errors;
    }
}
