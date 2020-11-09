package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {

    Mono<Event> createEvent(Event event);
    Flux<Event> findEvents(String fromDate, String toDate);
    Mono<Void> delete(Long id);
}
