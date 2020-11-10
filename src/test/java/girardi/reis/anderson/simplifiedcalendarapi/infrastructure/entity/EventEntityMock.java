package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.EventMock;
import girardi.reis.anderson.simplifiedcalendarapi.business.enumeration.FrequencyType;

public class EventEntityMock {

    public static final EventEntity NON_RECURRING_DAILY_EVENT_ENTITY = createNonRecurringDailyEventEntity();
    public static final EventEntity ALL_WEEK_DAYS_WEEKLY_RECURRING_EVENT_ENTITY = createWeeklyRecurringEventEntity();

    private static EventEntity createNonRecurringDailyEventEntity() {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(EventMock.EVENT_ID);
        eventEntity.setName(EventMock.EVENT_NAME);
        eventEntity.setDuration(EventMock.DURATION);
        eventEntity.setStartDateTime(EventMock.ORIGINAL_DAILY_EVENT_START_DATE_TIME.toLocalDateTime());

        return eventEntity;
    }

    private static EventEntity createWeeklyRecurringEventEntity() {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(EventMock.EVENT_ID);
        eventEntity.setName(EventMock.EVENT_NAME);
        eventEntity.setDuration(EventMock.DURATION);
        eventEntity.setStartDateTime(EventMock.FIRST_MONDAY.toLocalDateTime());

        eventEntity.setFrequencyType(FrequencyType.WEEKLY);
        eventEntity.setNumberOfOccurrences(3);
        eventEntity.setOccursOnSunday(true);
        eventEntity.setOccursOnMonday(true);
        eventEntity.setOccursOnTuesday(true);
        eventEntity.setOccursOnWednesday(true);
        eventEntity.setOccursOnThursday(true);
        eventEntity.setOccursOnFriday(true);
        eventEntity.setOccursOnSaturday(true);

        return eventEntity;
    }
}
