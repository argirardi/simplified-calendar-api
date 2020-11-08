package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import reactor.core.publisher.Mono;

public interface RecurrenceCreatable {

    Mono<Event> create(Event event);

}
