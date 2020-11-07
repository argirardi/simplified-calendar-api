package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.RecurrenceDTO;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception.InvalidEventException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import reactor.core.publisher.Mono;

public class FrequencyValidator implements Validatable<RecurrenceDTO> {

    protected static final String FREQUENCY_TYPE_FIELD = "frequencyType";

    @Override
    public Mono<RecurrenceDTO> validate(RecurrenceDTO recurrence) {

        Errors errors = new BeanPropertyBindingResult(recurrence, RecurrenceDTO.class.getSimpleName());
        validate(errors, recurrence);

        if (errors.hasErrors()) {
            return Mono.error(new InvalidEventException(errors.getAllErrors()));
        }

        return Mono.just(recurrence);
    }

    protected void validate(Errors errors, RecurrenceDTO recurrence) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FREQUENCY_TYPE_FIELD, "field.required");
    }
}
