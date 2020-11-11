package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.RecurrenceDTO;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception.InvalidEventException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class FrequencyValidator implements Validatable<RecurrenceDTO> {

    private static final String INVALID_END_RECURRENCE = "Either field End Recurrence Date or Number of Occurrences must be informed, but not both.";
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

        if (Objects.nonNull(recurrence.getEndRecurrenceDate()) && Objects.nonNull(recurrence.getNumberOfOccurrences())) {
            errors.rejectValue("endRecurrenceDate", "field.invalid", INVALID_END_RECURRENCE);
            errors.rejectValue("numberOfOccurrences", "field.invalid", INVALID_END_RECURRENCE);
        }

    }
}
