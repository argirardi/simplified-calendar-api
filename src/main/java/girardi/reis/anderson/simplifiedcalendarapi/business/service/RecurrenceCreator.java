package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class RecurrenceCreator {

    protected final EventRepositoryCustom eventRepository;
    protected final ModelMapper modelMapper;

    public RecurrenceCreator(final EventRepositoryCustom eventRepository,
                             final ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public Mono<Event> create(Event event) {

        if (event.isInfiniteRecurrence())
            return Mono.just(event);
        else
            return Flux.range(1, getNumberOfOccurrencesUntilTheEnd(event))
                    .flatMap(period -> cloneEvent(event, period))
                    .filter(this::skipUntil)
                    .flatMap(eventRepository::save)
                    .last()
                    .map(eventEntity -> event)
                    .switchIfEmpty(Mono.just(event));
    }

    protected boolean skipUntil(Event event) {
        return  event.getStartDateTime() != null;
    }

    private Mono<Event> cloneEvent(Event originalEvent, int period) {
        Event newEvent = originalEvent.clone();
        newEvent.setStartDateTime(getStartDateTime(originalEvent, period));
        return Mono.just(newEvent);
    }

    protected abstract ZonedDateTime getStartDateTime(Event event, Integer period);

    protected Integer getNumberOfOccurrencesUntilTheEnd(Event event) {

        if (Objects.nonNull(event.getRecurrence().getNumberOfOccurrences()))
            return getNumberOfOccurrences(event);
        else if (Objects.nonNull(event.getRecurrence().getEndRecurrenceDate()))
            return getRecurrencePeriod(Period.between(event.getStartDateTime().toLocalDate(), event.getRecurrence().getEndRecurrenceDate()));
        else
            return 0;
    }

    protected Integer getNumberOfOccurrences(Event event) {
        return event.getRecurrence().getNumberOfOccurrences();
    }

    protected abstract Integer getRecurrencePeriod(Period period);


}
