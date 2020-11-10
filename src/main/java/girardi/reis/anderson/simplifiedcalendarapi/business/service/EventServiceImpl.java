package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepositoryCustom eventRepositoryCustom;
    private final Map<String, RecurrenceCreator> recurrenceCreators;
    private final ModelMapper modelMapper;

    public EventServiceImpl(final EventRepositoryCustom eventRepositoryCustom,
                            final Map<String, RecurrenceCreator> recurrenceCreators,
                            final ModelMapper modelMapper) {
        this.eventRepositoryCustom = eventRepositoryCustom;
        this.recurrenceCreators = recurrenceCreators;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Event> createEvent(Event event) {
        return eventRepositoryCustom.save(event)
                              .map(eventEntity -> modelMapper.map(eventEntity, Event.class))
                              .flatMap(this::createRecurrences);
    }

    @Override
    public Flux<Event> findEvents(String fromDate, String toDate) {
        LocalDate fd = LocalDate.parse(fromDate);
        LocalDate td = LocalDate.parse(toDate);
        return eventRepositoryCustom.findInfiniteRecurrentEvents()
                              .flatMap(infiniteRecurrentEvent -> {
                                    return eventRepositoryCustom.findLastEventFromInfiniteRecurrence(infiniteRecurrentEvent.getId(), fromDate, toDate)
                                                                .flatMap(lastEventFromInfiniteRecurrence -> configureLastRecurrentEventForNewRecurrences(lastEventFromInfiniteRecurrence, fd, td))
                                                                .switchIfEmpty(configureNewEventForRecurrence(infiniteRecurrentEvent, fd, td))
                                                                .flatMap(this::createRecurrences);
                                }).thenMany(eventRepositoryCustom.findEvents(fromDate, toDate));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return eventRepositoryCustom.delete(id);
    }

    private Mono<Event> configureLastRecurrentEventForNewRecurrences(Event event, LocalDate fromDate, LocalDate toDate) {
        event.setId(event.getParentEventId());
        event.getRecurrence().setEndRecurrenceDate(toDate);
        return Mono.just(event);
    }

    private Mono<Event> configureNewEventForRecurrence(Event event, LocalDate fromDate, LocalDate toDate) {
           event.getRecurrence().setEndRecurrenceDate(toDate);
           return Mono.just(event);
    }

    private Mono<Event> createRecurrences(Event event) {
        if (event.isRecurrent()) {
            RecurrenceCreator recurrenceCreator = recurrenceCreators.get(event.getRecurrence().getFrequencyType().getCreatorClassName());
            return recurrenceCreator.create(event);
        } else {
            return Mono.just(event);
        }
    }
}