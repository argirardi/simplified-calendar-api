package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.RecurrenceDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Objects;

public class WeeklyFrequencyValidator extends FrequencyValidator {

    private static final String DAYS_OF_WEEK_FIELD = "daysOfWeek";

    protected void validate(Errors errors, RecurrenceDTO recurrence) {

        super.validate(errors, recurrence);

        if (Objects.isNull(errors.getFieldError(FREQUENCY_TYPE_FIELD))) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, DAYS_OF_WEEK_FIELD, "field.required");
        }
    }
}
