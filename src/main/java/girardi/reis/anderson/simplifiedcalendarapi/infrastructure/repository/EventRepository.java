package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository;

import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface EventRepository extends ReactiveCrudRepository<EventEntity, Long> {

    @Query("SELECT * " +
           "  FROM event" +
           " WHERE 1 = 1" +
           "   AND frequency_type is not null" +
           "   AND number_of_occurrences is null " +
           "   AND end_recurrence_date is null")
    Flux<EventEntity> findInfiniteRecurrentEvents();

    @Query("SELECT *" +
            "  FROM event" +
            "  WHERE 1 = 1" +
            "    AND date_trunc('day', start_date_time) >= :fromDate" +
            "    AND date_trunc('day', start_date_time) <= :toDate" +
            "  ORDER BY start_date_time")
    Flux<EventEntity> findEvents(LocalDate fromDate, LocalDate toDate);

    @Query("SELECT *" +
           "  FROM event" +
           " WHERE 1 = 1" +
           "   and id = (SELECT MAX(ID)" +
           "               FROM event" +
           "              WHERE parent_event_id = :id" +
           "                AND date_trunc('day', start_date_time) >= :fromDate " +
           "                AND date_trunc('day', start_date_time) <= :toDate " +
           ")")
    Mono<EventEntity> findLastEventFromInfiniteRecurrence(Long id, LocalDate fromDate, LocalDate toDate);

    @Query("DELETE" +
           "  FROM event" +
           " WHERE id = :id" +
           "    OR parent_event_id = :id")
    Mono<Void> delete(Long id);

}
