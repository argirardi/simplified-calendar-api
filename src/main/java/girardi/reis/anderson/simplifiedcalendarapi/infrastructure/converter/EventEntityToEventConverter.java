package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.converter;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Recurrence;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.modelmapper.AbstractConverter;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EventEntityToEventConverter extends AbstractConverter<EventEntity, Event> {

    @Override
    protected Event convert(EventEntity eventEntity) {

        Event event = new Event();

        event.setId(eventEntity.getId());
        event.setDuration(eventEntity.getDuration());
        event.setName(eventEntity.getName());
        event.setStartDateTime(eventEntity.getStartDateTime().atZone(ZoneId.systemDefault()));
        event.setParentEventId(eventEntity.getParentEventId());
        event.setRecurrence(convertToRecurrence(eventEntity));
        return event;
    }

    private Recurrence convertToRecurrence(EventEntity eventEntity) {

        Recurrence recurrence = null;

        if (Objects.nonNull(eventEntity.getFrequencyType())) {

            recurrence = new Recurrence();
            recurrence.setFrequencyType(eventEntity.getFrequencyType());
            recurrence.setDaysOfWeek(convertToDaysOfWeek(eventEntity));
            recurrence.setEndRecurrenceDate(eventEntity.getEndRecurrenceDate());
            recurrence.setNumberOfOccurrences(eventEntity.getNumberOfOccurrences());

        }

        return recurrence;
    }

    private Set<DayOfWeek> convertToDaysOfWeek(EventEntity eventEntity) {
        Set<DayOfWeek> daysOfWeek = new HashSet<>();

        if (eventEntity.isOccursOnSunday())
            daysOfWeek.add(DayOfWeek.SUNDAY);

        if (eventEntity.isOccursOnMonday())
            daysOfWeek.add(DayOfWeek.MONDAY);

        if (eventEntity.isOccursOnTuesday())
            daysOfWeek.add(DayOfWeek.TUESDAY);

        if (eventEntity.isOccursOnWednesday())
            daysOfWeek.add(DayOfWeek.WEDNESDAY);

        if (eventEntity.isOccursOnThursday())
            daysOfWeek.add(DayOfWeek.THURSDAY);

        if (eventEntity.isOccursOnFriday())
            daysOfWeek.add(DayOfWeek.FRIDAY);

        if (eventEntity.isOccursOnSaturday())
            daysOfWeek.add(DayOfWeek.SATURDAY);

        return daysOfWeek;
    }
}
