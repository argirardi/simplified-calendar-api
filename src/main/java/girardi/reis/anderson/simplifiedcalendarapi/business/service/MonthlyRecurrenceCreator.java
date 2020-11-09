package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;

@Component
public class MonthlyRecurrenceCreator extends DailyRecurrenceCreator {

    public MonthlyRecurrenceCreator(EventRepositoryCustom eventRepository,
                                    ModelMapper modelMapper) {
        super(eventRepository, modelMapper);
    }

    @Override
    protected ZonedDateTime getStartDateTime(Event event, Integer period) {
        return event.getStartDateTime().plusMonths(period);
    }

    @Override
    protected Integer getRecurrencePeriod(Period period) {
        return period.getMonths() -1;
    }
}
