package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

public interface RecurrenceCreatable {

    Mono<Event> create(Event event);

}
