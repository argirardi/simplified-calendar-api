package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class InvalidEventException extends RuntimeException {

    private List<ObjectError> errors;

    public InvalidEventException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public InvalidEventException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
