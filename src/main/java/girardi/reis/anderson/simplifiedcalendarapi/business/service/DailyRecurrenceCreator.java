package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Recurrence;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

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

        return endOriginalEvent(event)
                .flatMapMany(eventEntity -> Flux.range(1, getNumberOfOccurrencesUntilTheEnd(event.getRecurrence())))
                .flatMap(days -> cloneEvent(event, days))
                .flatMap(this::save)
                .last()
                .map(eventEntity -> event);
    }

    private Mono<EventEntity> endOriginalEvent(Event event) {
        event.getRecurrence().setEndRecurrenceDate(event.getStartDateTime().toLocalDate());
        return save(event);
    }

    private Mono<EventEntity> save (Event event) {
        return eventRepository.save(modelMapper.map(event, EventEntity.class));
    }

    private Mono<Event> cloneEvent(Event originalEvent, int days) {
        Event newEvent = originalEvent.clone();
        newEvent.setStartDateTime(originalEvent.getStartDateTime().plusDays(days));
        newEvent.getRecurrence().setEndRecurrenceDate(newEvent.getStartDateTime().toLocalDate());
        return Mono.just(newEvent);
    }

    protected Integer getNumberOfOccurrencesUntilTheEnd(Recurrence recurrence) {

        if (Objects.nonNull(recurrence.getNumberOfOccurrences()))
            return recurrence.getNumberOfOccurrences() - 1;
        else if (Objects.nonNull(recurrence.getEndRecurrenceDate()))
            return Period.between(LocalDate.now(), recurrence.getEndRecurrenceDate()).getDays() - 1;
        else
            return 0;
    }
}
