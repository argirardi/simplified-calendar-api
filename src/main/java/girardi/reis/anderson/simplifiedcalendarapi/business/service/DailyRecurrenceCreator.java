package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;

@Component
public class DailyRecurrenceCreator extends RecurrenceCreator {

    public DailyRecurrenceCreator(final EventRepositoryCustom eventRepository,
                                  final ModelMapper modelMapper) {
        super(eventRepository, modelMapper);
    }

    protected LocalDateTime getStartDateTime(Event event, Integer period) {
        return event.getStartDateTime().plusDays(period);
    }

    protected Integer getRecurrencePeriod(Period period) {
        return period.getDays();
    }
}