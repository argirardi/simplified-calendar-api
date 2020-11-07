package girardi.reis.anderson.simplifiedcalendarapi.api.v1.handler;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventDTO;
import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.EventService;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.RecurrenceCreatable;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator.Validatable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class EventHandler {

    private final Validatable<EventDTO> validator;
    private final ModelMapper modelMapper;
    private final EventService eventService;

    public EventHandler(final Validatable<EventDTO> validator,
                        final ModelMapper modelMapper,
                        final EventService eventService) {
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.eventService = eventService;
    }

    public Mono<ServerResponse> createEvent(ServerRequest request) {

        return request.body(BodyExtractors.toMono(EventDTO.class))
                      .flatMap(validator::validate)
                      .map(eventDTO -> modelMapper.map(eventDTO, Event.class))
                      .flatMap(eventService::createEvent)
                      .flatMap(event -> ServerResponse.ok().bodyValue(event));
    }


}
