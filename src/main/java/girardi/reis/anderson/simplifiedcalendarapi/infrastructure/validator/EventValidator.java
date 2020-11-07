package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventDTO;
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
public class EventValidator implements Validatable<EventDTO>{

    private static final String INVALID_DURATION_MESSAGE_TEMPLATE = "The event duration must be lesser than %s minutes.";

    public static final String NAME_FIELD = "name";
    public static final String START_DATE_TIME_FIELD = "startDateTime";
    public static final String DURATION_FIELD = "duration";

    @Override
    public Mono<EventDTO> validate(EventDTO event) {

        Errors errors = new BeanPropertyBindingResult(event, EventDTO.class.getSimpleName());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME_FIELD, "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, START_DATE_TIME_FIELD, "field.required");
        ValidationUtils.rejectIfEmpty(errors, DURATION_FIELD, "field.required");
        validateSpanningEvent(errors, event);

        if (errors.hasErrors()) {
            return Mono.error(new InvalidEventException(errors.getAllErrors()));
        }

        return validateRecurrence(event.getRecurrence())
               .switchIfEmpty(Mono.just(new RecurrenceDTO()))
               .map(recurrence -> event) ;
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

    private void validateSpanningEvent(Errors errors, EventDTO event) {
        if (isStartDateTimeAndDurationValid(errors)) {

            if (isSpanningEvent(event)) {
                errors.rejectValue(DURATION_FIELD, "field.required", String.format(INVALID_DURATION_MESSAGE_TEMPLATE, event.getDuration()));
            }
        }

    }

    private boolean isStartDateTimeAndDurationValid(Errors errors) {
        return Objects.isNull(errors.getFieldError(START_DATE_TIME_FIELD)) && Objects.isNull(errors.getFieldError(DURATION_FIELD));
    }

    private boolean isSpanningEvent(EventDTO event) {
        ZonedDateTime nextDay = event.getStartDateTime().plusDays(1).truncatedTo(ChronoField.DAY_OF_MONTH.getBaseUnit());
        ZonedDateTime endDateTime = event.getStartDateTime().plusMinutes(event.getDuration());
        return !endDateTime.isBefore(nextDay);
    }
}
