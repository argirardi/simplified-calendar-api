package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final Map<String, RecurrenceCreatable> recurrenceCreators;
    private final ModelMapper modelMapper;

    public EventServiceImpl(final EventRepository eventRepository,
                            final Map<String, RecurrenceCreatable> recurrenceCreators,
                            final ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.recurrenceCreators = recurrenceCreators;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Event> createEvent(Event event) {

        return eventRepository.save(modelMapper.map(event, EventEntity.class))
                              .map(eventEntity -> modelMapper.map(eventEntity, Event.class))
                              .flatMap(this::createRecurrences);
    }

    private Mono<Event> createRecurrences(Event event) {
        if (event.isRecurrent()) {
            RecurrenceCreatable recurrenceCreator = recurrenceCreators.get(event.getRecurrence().getFrequencyType().getCreatorClassName());
            return recurrenceCreator.create(event);
        } else {
            return Mono.just(event);
        }
    }

    private Mono<EventRequestDTO> convertToDto(Event event) {
        return Mono.just(modelMapper.map(event, EventRequestDTO.class));
    }


}