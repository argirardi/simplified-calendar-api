package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DailyRecurrenceCreator implements RecurrenceCreatable {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public DailyRecurrenceCreator(final EventRepository eventRepository,
                                  final ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Event> create(Event event) {

        return Flux.range(1, event.getRecurrence().getNumberOfOccurrencesUntilTheEnd())
                   .flatMap(days -> cloneEvent(event, days))
                   .map(eventRepository::save)
                   .last();
    }

    private Mono<Event> cloneEvent(Event originalEvent, int days) {
        Event newEvent = modelMapper.map(originalEvent, Event.class);

        //newEvent.setId(null);
        newEvent.setParentEvent(originalEvent);
        newEvent.setStartDateTime(originalEvent.getStartDateTime().plusDays(days));

        return Mono.just(newEvent);
    }


}
