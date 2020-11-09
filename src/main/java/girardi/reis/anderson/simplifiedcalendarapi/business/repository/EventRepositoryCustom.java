package girardi.reis.anderson.simplifiedcalendarapi.business.repository;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventRepositoryCustom {

    Mono<Event> save(Event event);
    Flux<Event> findInfiniteRecurrentEvents();
    Flux<Event> findEvents(String fromDate, String toDate);
    Mono<Event> findLastEventFromInfiniteRecurrence(Long id, String fromDate, String toDate);
    Mono<Void> delete(Long id);

}
