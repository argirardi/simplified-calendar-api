package girardi.reis.anderson.simplifiedcalendarapi.business.repository;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface EventRepositoryCustom {

    Mono<Event> save(Event event);
    Flux<Event> findInfiniteRecurrentEvents();
    Flux<Event> findEvents(LocalDate fromDate, LocalDate toDate);
    Mono<Event> findLastEventFromInfiniteRecurrence(Long id, LocalDate fromDate, LocalDate toDate);
    Mono<Void> delete(Long id);

}
