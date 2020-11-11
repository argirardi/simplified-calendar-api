package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public class EventRepositoryCustomImpl implements EventRepositoryCustom  {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventRepositoryCustomImpl(final EventRepository eventRepository,
                                     final ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Event> save(Event event) {
        return eventRepository.save(modelMapper.map(event, EventEntity.class))
                .map(eventEntity -> modelMapper.map(eventEntity, Event.class));
    }

    @Override
    public Flux<Event> findInfiniteRecurrentEvents() {
        return eventRepository.findInfiniteRecurrentEvents()
                              .map(eventEntity -> modelMapper.map(eventEntity, Event.class));
    }

    @Override
    public Flux<Event> findEvents(LocalDate fromDate, LocalDate toDate) {
        return eventRepository.findEvents(fromDate, toDate)
                .map(eventEntity -> modelMapper.map(eventEntity, Event.class));
    }

    @Override
    public Mono<Event> findLastEventFromInfiniteRecurrence(Long id, LocalDate fromDate, LocalDate toDate) {
        return eventRepository.findLastEventFromInfiniteRecurrence(id, fromDate, toDate)
                .map(eventEntity -> modelMapper.map(eventEntity, Event.class));

    }

    @Override
    public Mono<Void> delete(Long id) {
        return eventRepository.delete(id);
    }
}
