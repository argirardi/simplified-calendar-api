package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface EventService {

    Mono<Event> createEvent(Event event);
    Flux<Event> findEvents(LocalDate fromDate, LocalDate toDate);
    Mono<Void> delete(Long id);
}
