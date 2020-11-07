package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MonthlyRecurrenceCreator implements RecurrenceCreatable {

    @Override
    public Mono<Event> create(Event event) {
        return null;
    }
}
