package girardi.reis.anderson.simplifiedcalendarapi.api.v1.validator;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.RecurrenceDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.enumeration.FrequencyType;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception.InvalidEventException;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator.EventValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class EventRequestDTOValidatorTest {

    private static final String INVALID_DURATION_MESSAGE_TEMPLATE = "The event duration must be lesser than %s minutes.";
    private static final int YEAR = 2020;
    private static final int MONTH = 10;
    private static final int DAY_OF_MONTH = 5;
    private static final int HOUR = 23;
    private static final int MINUTE = 30;
    private static final int SECOND = 0;
    private static final int NANO_OF_SECOND = 0;
    private static final int EXPECTED_ONLY_ONE_ERROR = 1;
    private static final int DURATION_THIRTY_MINUTES = 30;
    private static final int DURATION_TWENTY_NINE_MINUTES = 29;

    private EventValidator eventValidator = new EventValidator();
    private LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND, NANO_OF_SECOND);

    @Test
    public void givenAllFieldsAreNullThenMustInvalidateRequest() {

        StepVerifier.create(eventValidator.validate(new EventRequestDTO()))
                .consumeErrorWith(throwable -> Assertions.assertEquals(3, ((InvalidEventException) throwable).getErrors().size()))
                .verify();
    }

    @Test
    public void givenEventSpansToNextDayThenMustInvalidateRequest() {


        EventRequestDTO spanningEvent = new EventRequestDTO();
        spanningEvent.setName("Spanning Event");
        spanningEvent.setStartDateTime(startDateTime);
        spanningEvent.setDuration(DURATION_THIRTY_MINUTES);

        StepVerifier.create(eventValidator.validate(spanningEvent))
                .consumeErrorWith(throwable -> {
                    InvalidEventException invalidEventException = (InvalidEventException) throwable;
                    Assertions.assertEquals(EXPECTED_ONLY_ONE_ERROR, invalidEventException.getErrors().size());
                    Assertions.assertEquals(String.format(INVALID_DURATION_MESSAGE_TEMPLATE, spanningEvent.getDuration()), invalidEventException.getErrors().get(0).getDefaultMessage());
                })
                .verify();
    }

    @Test
    public void givenEventEndsBeforeNextDayThenMustValidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                .expectNext(singleDayEvent)
                .verifyComplete();
    }

    @Test
    public void givenRecurrenceEventIsInvalidThenMustInvalidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);
        singleDayEvent.setRecurrence(new RecurrenceDTO());

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                    .consumeErrorWith(throwable -> Assertions.assertEquals(1, ((InvalidEventException) throwable).getErrors().size()))
                    .verify();
    }

    @Test
    public void givenDailyRecurrenceEventIsValidThenMustValidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);

        RecurrenceDTO recurrence = new RecurrenceDTO();
        recurrence.setFrequencyType(FrequencyType.DAILY);

        singleDayEvent.setRecurrence(recurrence);

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                .expectNext(singleDayEvent)
                .verifyComplete();
    }

    @Test
    public void givenWeeklyRecurrenceEventIsInvalidThenMustInvalidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);

        RecurrenceDTO recurrence = new RecurrenceDTO();
        recurrence.setFrequencyType(FrequencyType.WEEKLY);

        singleDayEvent.setRecurrence(recurrence);

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                .consumeErrorWith(throwable -> Assertions.assertEquals(1, ((InvalidEventException) throwable).getErrors().size()))
                .verify();
    }

    @Test
    public void givenWeeklyRecurrenceEventIsValidThenMustValidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);

        RecurrenceDTO recurrence = new RecurrenceDTO();
        recurrence.setFrequencyType(FrequencyType.WEEKLY);
        recurrence.setDaysOfWeek(Set.of(DayOfWeek.FRIDAY));

        singleDayEvent.setRecurrence(recurrence);

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                .expectNext(singleDayEvent)
                .verifyComplete();
    }

    @Test
    public void givenMonthlyRecurrenceEventIsValidThenMustValidateRequest() {

        EventRequestDTO singleDayEvent = new EventRequestDTO();
        singleDayEvent.setName("Single Day Event");
        singleDayEvent.setStartDateTime(startDateTime);
        singleDayEvent.setDuration(DURATION_TWENTY_NINE_MINUTES);

        RecurrenceDTO recurrence = new RecurrenceDTO();
        recurrence.setFrequencyType(FrequencyType.MONTHLY);

        singleDayEvent.setRecurrence(recurrence);

        StepVerifier.create(eventValidator.validate(singleDayEvent))
                .expectNext(singleDayEvent)
                .verifyComplete();
    }
}
