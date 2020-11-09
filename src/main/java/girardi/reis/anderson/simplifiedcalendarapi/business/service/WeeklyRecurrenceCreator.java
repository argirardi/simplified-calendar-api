package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;

@Component
public class WeeklyRecurrenceCreator extends RecurrenceCreator {
    public WeeklyRecurrenceCreator(final EventRepositoryCustom eventRepository,
                                   final ModelMapper modelMapper) {
        super(eventRepository, modelMapper);
    }

    @Override
    protected LocalDateTime getStartDateTime(Event event, Integer period) {
        return event.getStartDateTime().plusWeeks(period);
    }

    @Override
    protected Integer getRecurrencePeriod(Period period) {
        return period.getDays() / 7;
    }
}
