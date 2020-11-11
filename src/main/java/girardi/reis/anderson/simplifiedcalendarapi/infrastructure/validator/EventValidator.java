package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.RecurrenceDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.enumeration.FrequencyType;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception.InvalidEventException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

@Component
public class EventValidator implements Validatable<EventRequestDTO>{

    private static final String DURATION_MUST_BE_GREATER_THAN_ZERO = "The event duration must be greater than 0 minute.";
    private static final String INVALID_MAXIMUM_DURATION_MESSAGE_TEMPLATE = "The event duration must be lesser than %s minutes.";

    public static final String NAME_FIELD = "name";
    public static final String START_DATE_TIME_FIELD = "startDateTime";
    public static final String DURATION_FIELD = "duration";

    @Override
    public Mono<EventRequestDTO> validate(EventRequestDTO event) {

        Errors errors = new BeanPropertyBindingResult(event, EventRequestDTO.class.getSimpleName());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME_FIELD, "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, START_DATE_TIME_FIELD, "field.required");
        validateDuration(errors, event);
        validateSpanningEvent(errors, event);

        if (errors.hasErrors()) {
            return Mono.error(new InvalidEventException(errors.getAllErrors()));
        }

        return validateRecurrence(event.getRecurrence())
               .switchIfEmpty(Mono.just(new RecurrenceDTO()))
               .map(recurrence -> event) ;
    }

    private void validateDuration(Errors errors, EventRequestDTO event) {

        if (Objects.isNull(event.getDuration()) || event.getDuration() == 0) {
            errors.rejectValue(DURATION_FIELD, "field.invalid", DURATION_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    private Mono<RecurrenceDTO> validateRecurrence(RecurrenceDTO recurrence) {

        if (Objects.nonNull(recurrence)) {
            return Optional.ofNullable(recurrence.getFrequencyType())
                           .orElse(FrequencyType.DAILY)
                           .getValidatable()
                           .orElse(new FrequencyValidator())
                           .validate(recurrence);
        } else {
            return Mono.empty();
        }
    }

    private void validateSpanningEvent(Errors errors, EventRequestDTO event) {
        if (isStartDateTimeAndDurationValid(errors)) {

            if (isSpanningEvent(event)) {
                errors.rejectValue(DURATION_FIELD, "field.required", String.format(INVALID_MAXIMUM_DURATION_MESSAGE_TEMPLATE, event.getDuration()));
            }
        }

    }

    private boolean isStartDateTimeAndDurationValid(Errors errors) {
        return Objects.isNull(errors.getFieldError(START_DATE_TIME_FIELD)) && Objects.isNull(errors.getFieldError(DURATION_FIELD));
    }

    private boolean isSpanningEvent(EventRequestDTO event) {
        ZonedDateTime nextDay = event.getStartDateTime().plusDays(1).truncatedTo(ChronoField.DAY_OF_MONTH.getBaseUnit());
        ZonedDateTime endDateTime = event.getStartDateTime().plusMinutes(event.getDuration());
        return !endDateTime.isBefore(nextDay);
    }
}
