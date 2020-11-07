package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final Map<String, RecurrenceCreatable> recurrenceCreators;

    public EventServiceImpl(final EventRepository eventRepository,
                            final Map<String, RecurrenceCreatable> recurrenceCreators) {
        this.eventRepository = eventRepository;
        this.recurrenceCreators = recurrenceCreators;
    }

    @Override
    public Mono<Event> createEvent(Event event) {

        return Mono.just(eventRepository.save(event))
                   .map(e -> e.getRecurrence())
                   .filter(Objects::nonNull)
                   .flatMap(recurrence -> recurrenceCreators.get(recurrence.getFrequencyType().getCreatorClassName()).create(event));

    }
}
