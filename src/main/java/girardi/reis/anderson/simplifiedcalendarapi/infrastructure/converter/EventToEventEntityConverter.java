package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.converter;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Recurrence;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.modelmapper.AbstractConverter;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EventToEventEntityConverter extends AbstractConverter<Event, EventEntity> {

    @Override
    protected EventEntity convert(Event event) {

        EventEntity eventEntity = new EventEntity();

        eventEntity.setId(event.getId());
        eventEntity.setDuration(event.getDuration());
        eventEntity.setName(event.getName());
        eventEntity.setStartDateTime(event.getStartDateTime().toLocalDateTime());
        eventEntity.setParentEventId(event.getParentEventId());
        setEventEntityFieldsFromRecurrence(event.getRecurrence(), eventEntity);

        return eventEntity;
    }

    private void setEventEntityFieldsFromRecurrence(Recurrence recurrence, EventEntity eventEntity) {

        if (Objects.nonNull(recurrence)) {
            eventEntity.setEndRecurrenceDate(recurrence.getEndRecurrenceDate());
            eventEntity.setFrequencyType(recurrence.getFrequencyType());
            eventEntity.setNumberOfOccurrences(recurrence.getNumberOfOccurrences());

            eventEntity.setOccursOnSunday(recurrence.getDaysOfWeek().contains(DayOfWeek.SUNDAY));
            eventEntity.setOccursOnMonday(recurrence.getDaysOfWeek().contains(DayOfWeek.MONDAY));
            eventEntity.setOccursOnTuesday(recurrence.getDaysOfWeek().contains(DayOfWeek.TUESDAY));
            eventEntity.setOccursOnWednesday(recurrence.getDaysOfWeek().contains(DayOfWeek.WEDNESDAY));
            eventEntity.setOccursOnThursday(recurrence.getDaysOfWeek().contains(DayOfWeek.THURSDAY));
            eventEntity.setOccursOnFriday(recurrence.getDaysOfWeek().contains(DayOfWeek.FRIDAY));
            eventEntity.setOccursOnSaturday(recurrence.getDaysOfWeek().contains(DayOfWeek.SATURDAY));
        }
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
            daysOfWeek.add(DayOfWeek.WEDNESDAY);

        return daysOfWeek;
    }
}
