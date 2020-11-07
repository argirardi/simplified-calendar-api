package girardi.reis.anderson.simplifiedcalendarapi.api.v1.enumeration;

import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator.FrequencyValidator;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator.WeeklyFrequencyValidator;

import java.util.Optional;

public enum FrequencyType {

    DAILY(Optional.empty()),
    WEEKLY(Optional.of(new WeeklyFrequencyValidator())),
    MONTHLY(Optional.empty());

    private Optional<FrequencyValidator> validatable;

    FrequencyType(Optional<FrequencyValidator> validatable) {
        this.validatable = validatable;
    }

    public Optional<FrequencyValidator> getValidatable() {
        return validatable;
    }
}
