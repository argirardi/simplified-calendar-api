package girardi.reis.anderson.simplifiedcalendarapi.business.domain;

import girardi.reis.anderson.simplifiedcalendarapi.business.enumeration.FrequencyType;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class EventMock {
    public static final Long EVENT_ID = 1L;
    public static final short DURATION = (short) 30;
    public static final String EVENT_NAME = "Event for testing";

    private static final int YEAR = 2020;
    private static final int MONTH = 11;
    private static final int DAY_OF_MONTH = 6;
    private static final int HOUR = 10;
    private static final int MINUTE = 0;
    private static final int SECOND = 0;
    private static final int NANO_OF_SECOND = 0;
    public static final ZonedDateTime ORIGINAL_DAILY_EVENT_START_DATE_TIME = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final int NUMBER_OF_DAILY_OCCURRENCES = 3;
    private static final int NUMBER_OF_WEEKLY_OCCURRENCES = 3;
    private static final int ONE_DAY = 1;
    private static final int TWO_DAYS = 2;
    private static final int THREE_DAYS = 3;
    private static final int FOUR_DAYS = 4;
    private static final int FIVE_DAYS = 5;
    private static final int FIFTEEN_DAYS = 15;
    public static final int ONE_MONTH = 1;
    public static final int TWO_MONTHS = 2;
    public static final int THREE_MONTHS = 3;
    public static final int FOUR_MONTHS = 4;
    public static final int FIVE_MONTHS = 5;
    private static final int DAY_OF_MONTH_TWO = 2;
    private static final int DAY_OF_MONTH_FOUR = 4;
    private static final int DAY_OF_MONTH_SIX = 6;
    private static final int DAY_OF_MONTH_NINE = 9;
    private static final int DAY_OF_MONTH_ELEVEN = 11;
    private static final int DAY_OF_MONTH_THIRTEEN = 13;
    private static final int DAY_OF_MONTH_SIXTEEN = 16;
    private static final int DAY_OF_MONTH_EIGHTEEN = 18;
    private static final int DAY_OF_MONTH_TWENTY = 20;

    public static final ZonedDateTime FIRST_MONDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_TWO, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime FIRST_WEDNESDAY  = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_FOUR, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime FIRST_FRIDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_SIX, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());

    private static final ZonedDateTime SECOND_MONDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_NINE, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime SECOND_WEDNESDAY  = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_ELEVEN, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime SECOND_FRIDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_THIRTEEN, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());

    private static final ZonedDateTime THIRD_MONDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_SIXTEEN, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime THIRD_WEDNESDAY  = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_EIGHTEEN, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());
    private static final ZonedDateTime THIRD_FRIDAY     = ZonedDateTime.of(YEAR, MONTH, DAY_OF_MONTH_TWENTY, HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZoneId.systemDefault());

    public static final Event NON_RECURRING_DAILY_EVENT                 = createNonRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME);
    public static final Event ORIGINAL_DAILY_RECURRING_EVENT            = createFixedRecurrencesDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME);
    public static final Event FIRST_DAILY_RECURRING_EVENT               = createFixedRecurrencesDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(ONE_DAY));
    public static final Event SECOND_DAILY_RECURRING_EVENT              = createFixedRecurrencesDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(TWO_DAYS));

    public static final Event ORIGINAL_DATED_DAILY_RECURRING_EVENT      = createDatedDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME);
    public static final Event FIRST_DATED_DAILY_RECURRING_EVENT         = createDatedDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(ONE_DAY));
    public static final Event SECOND_DATED_DAILY_RECURRING_EVENT        = createDatedDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(TWO_DAYS));
    public static final Event THIRD_DATED_DAILY_RECURRING_EVENT         = createDatedDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(THREE_DAYS));
    public static final Event FOURTH_DATED_DAILY_RECURRING_EVENT        = createDatedDailyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(FOUR_DAYS));

    public static final Event ORIGINAL_DAILY_ENDLESS_RECURRING_EVENT    = createEndlessRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME, FrequencyType.DAILY);

    public static final Event ALL_WEEK_DAYS_WEEKLY_RECURRING_EVENT      = createFixedRecurrencesAllWeeklyRecurringEvent(FIRST_MONDAY);
    public static final Event ORIGINAL_WEEKLY_RECURRING_EVENT           = createFixedRecurrencesWeeklyRecurringEvent(FIRST_MONDAY);
    public static final Event SECOND_WEEKLY_RECURRING_EVENT             = createFixedRecurrencesWeeklyRecurringEvent(FIRST_WEDNESDAY);
    public static final Event THIRD_WEEKLY_RECURRING_EVENT              = createFixedRecurrencesWeeklyRecurringEvent(FIRST_FRIDAY);
    public static final Event FOURTH_WEEKLY_RECURRING_EVENT             = createFixedRecurrencesWeeklyRecurringEvent(SECOND_MONDAY);
    public static final Event FIFTH_WEEKLY_RECURRING_EVENT              = createFixedRecurrencesWeeklyRecurringEvent(SECOND_WEDNESDAY);
    public static final Event SIXTH_WEEKLY_RECURRING_EVENT              = createFixedRecurrencesWeeklyRecurringEvent(SECOND_FRIDAY);
    public static final Event SEVENTH_WEEKLY_RECURRING_EVENT            = createFixedRecurrencesWeeklyRecurringEvent(THIRD_MONDAY);
    public static final Event EIGHTH_WEEKLY_RECURRING_EVENT             = createFixedRecurrencesWeeklyRecurringEvent(THIRD_WEDNESDAY);
    public static final Event NINETIETH_WEEKLY_RECURRING_EVENT          = createFixedRecurrencesWeeklyRecurringEvent(THIRD_FRIDAY);
    public static final Event ORIGINAL_DATED_WEEKLY_RECURRING_EVENT     = createDatedWeeklyRecurringEvent(FIRST_MONDAY);
    public static final Event SECOND_DATED_WEEKLY_RECURRING_EVENT       = createDatedWeeklyRecurringEvent(FIRST_WEDNESDAY);
    public static final Event THIRD_DATED_WEEKLY_RECURRING_EVENT        = createDatedWeeklyRecurringEvent(FIRST_FRIDAY);
    public static final Event FOURTH_DATED_WEEKLY_RECURRING_EVENT       = createDatedWeeklyRecurringEvent(SECOND_MONDAY);
    public static final Event FIFTH_DATED_WEEKLY_RECURRING_EVENT        = createDatedWeeklyRecurringEvent(SECOND_WEDNESDAY);
    public static final Event SIXTH_DATED_WEEKLY_RECURRING_EVENT        = createDatedWeeklyRecurringEvent(SECOND_FRIDAY);
    public static final Event SEVENTH_DATED_WEEKLY_RECURRING_EVENT      = createDatedWeeklyRecurringEvent(THIRD_MONDAY);
    public static final Event ORIGINAL_WEEKLY_ENDLESS_RECURRING_EVENT    = createWeeklyEndlessRecurringEvent(FIRST_MONDAY);

    public static final Event ORIGINAL_MONTHLY_RECURRING_EVENT            = createFixedRecurrencesMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME);
    public static final Event FIRST_MONTHLY_RECURRING_EVENT               = createFixedRecurrencesMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(ONE_MONTH));
    public static final Event SECOND_MONTHLY_RECURRING_EVENT              = createFixedRecurrencesMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(TWO_MONTHS));

    public static final Event ORIGINAL_DATED_MONTHLY_RECURRING_EVENT      = createDatedMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME);
    public static final Event FIRST_DATED_MONTHLY_RECURRING_EVENT         = createDatedMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(ONE_MONTH));
    public static final Event SECOND_DATED_MONTHLY_RECURRING_EVENT        = createDatedMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(TWO_MONTHS));
    public static final Event THIRD_DATED_MONTHLY_RECURRING_EVENT         = createDatedMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(THREE_MONTHS));
    public static final Event FOURTH_DATED_MONTHLY_RECURRING_EVENT        = createDatedMonthlyRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(FOUR_MONTHS));

    public static final Event ORIGINAL_MONTHLY_ENDLESS_RECURRING_EVENT    = createEndlessRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME, FrequencyType.MONTHLY);

    private static Event createEvent(ZonedDateTime startDateTime) {
        Event originalEvent = new Event();
        originalEvent.setName(EVENT_NAME);
        originalEvent.setStartDateTime(startDateTime);
        originalEvent.setDuration(DURATION);
        return originalEvent;
    }

    private static Event createNonRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);
        originalEvent.setId(EVENT_ID);
        return originalEvent;
    }


    private static Recurrence createRecurrence(FrequencyType daily) {
        Recurrence recurrence = new Recurrence();
        recurrence.setFrequencyType(daily);
        return recurrence;
    }

    private static Event createFixedRecurrencesDailyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.DAILY);
        recurrence.setNumberOfOccurrences(NUMBER_OF_DAILY_OCCURRENCES);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createDatedDailyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.DAILY);
        recurrence.setEndRecurrenceDate(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusDays(FIVE_DAYS).toLocalDate());
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createEndlessRecurringEvent(ZonedDateTime startDateTime, FrequencyType frequencyType) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(frequencyType);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createFixedRecurrencesWeeklyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.WEEKLY);
        recurrence.setDaysOfWeek(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        recurrence.setNumberOfOccurrences(NUMBER_OF_WEEKLY_OCCURRENCES);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createFixedRecurrencesAllWeeklyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createNonRecurringEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.WEEKLY);
        recurrence.setDaysOfWeek(Set.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
        recurrence.setNumberOfOccurrences(NUMBER_OF_WEEKLY_OCCURRENCES);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }
    private static Event createDatedWeeklyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.WEEKLY);
        recurrence.setDaysOfWeek(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        recurrence.setEndRecurrenceDate(FIRST_MONDAY.plusDays(FIFTEEN_DAYS).toLocalDate());
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createWeeklyEndlessRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEndlessRecurringEvent(ORIGINAL_DAILY_EVENT_START_DATE_TIME, FrequencyType.WEEKLY);;
        originalEvent.getRecurrence().setDaysOfWeek(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        return originalEvent;
    }

    private static Event createFixedRecurrencesMonthlyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.MONTHLY);
        recurrence.setNumberOfOccurrences(NUMBER_OF_DAILY_OCCURRENCES);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }


    private static Event createDatedMonthlyRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.MONTHLY);
        recurrence.setEndRecurrenceDate(ORIGINAL_DAILY_EVENT_START_DATE_TIME.plusMonths(FIVE_MONTHS).toLocalDate());
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }

    private static Event createMonthlyEndlessRecurringEvent(ZonedDateTime startDateTime) {
        Event originalEvent = createEvent(startDateTime);

        Recurrence recurrence = createRecurrence(FrequencyType.MONTHLY);
        originalEvent.setRecurrence(recurrence);
        return originalEvent;
    }


}
