package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.time.ZonedDateTime;

@Component
public class WeeklyRecurrenceCreator extends RecurrenceCreator {

    public static final int NUMBER_DAYS_A_WEEK = 7;

    public WeeklyRecurrenceCreator(final EventRepositoryCustom eventRepository,
                                   final ModelMapper modelMapper) {
        super(eventRepository, modelMapper);
    }

    @Override
    protected boolean isMustCreateRecurrentEvent(Event event) {
        return  event.getRecurrence().getDaysOfWeek().contains(event.getStartDateTime().getDayOfWeek());
    }

    @Override
    protected ZonedDateTime getStartDateTime(Event event, Integer period) {
        return event.getStartDateTime().plusDays(period);
    }

    @Override
    protected Integer getRecurrencePeriod(Period period) {
        return period.getDays() - ONE_DAY;
    }

    @Override
    protected Integer getNumberOfOccurrences(Event event) {
        return event.getRecurrence().getNumberOfOccurrences()  * NUMBER_DAYS_A_WEEK - ONE_DAY;
    }
}