package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepositoryCustomImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepositoryCustom eventRepository;
    private final Map<String, RecurrenceCreator> recurrenceCreators;
    private final ModelMapper modelMapper;

    public EventServiceImpl(final EventRepositoryCustomImpl eventRepository,
                            final Map<String, RecurrenceCreator> recurrenceCreators,
                            final ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.recurrenceCreators = recurrenceCreators;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Event> createEvent(Event event) {

        return eventRepository.save(event)
                              .map(eventEntity -> modelMapper.map(eventEntity, Event.class))
                              .flatMap(this::createRecurrences);
    }

    @Override
    public Flux<Event> findEvents(String fromDate, String toDate) {
        LocalDate fd = LocalDate.parse(fromDate);
        LocalDate td = LocalDate.parse(toDate);
        return eventRepository.findInfiniteRecurrentEvents()
                              .flatMap(infiniteRecurrentEvent -> {
                                    return eventRepository.findLastEventFromInfiniteRecurrence(infiniteRecurrentEvent.getId(), fromDate, toDate)
                                                          .flatMap(lastEventFromInfiniteRecurrence -> configureLastRecurrentEventForNewRecurrences(lastEventFromInfiniteRecurrence, fd, td))
                                                          .switchIfEmpty(configureNewEventForRecurrence(infiniteRecurrentEvent, fd, td))
                                                          .flatMap(this::createRecurrences);
                                }).thenMany(eventRepository.findEvents(fromDate, toDate));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return eventRepository.delete(id);
    }

    private Mono<Event> configureLastRecurrentEventForNewRecurrences(Event event, LocalDate fromDate, LocalDate toDate) {
        event.setId(event.getParentEventId());
        event.getRecurrence().setEndRecurrenceDate(toDate);
        return Mono.just(event);
    }

    private Mono<Event> configureNewEventForRecurrence(Event event, LocalDate fromDate, LocalDate toDate) {
           event.setStartDateTime(ZonedDateTime.of(fromDate.minusDays(1), event.getStartDateTime().toLocalTime(), ZoneId.systemDefault()));
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