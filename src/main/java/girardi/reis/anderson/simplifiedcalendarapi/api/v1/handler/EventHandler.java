package girardi.reis.anderson.simplifiedcalendarapi.api.v1.handler;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventResponseDTO;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.EventService;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator.Validatable;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class EventHandler {

    private final Validatable<EventRequestDTO> validator;
    private final ModelMapper modelMapper;
    private final EventService eventService;

    public EventHandler(final Validatable<EventRequestDTO> validator,
                        final ModelMapper modelMapper,
                        final EventService eventService) {
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.eventService = eventService;
    }

    public Mono<ServerResponse> createEvent(ServerRequest request) {

        return request.body(BodyExtractors.toMono(EventRequestDTO.class))
                      .flatMap(validator::validate)
                      .map(eventDTO -> modelMapper.map(eventDTO, Event.class))
                      .flatMap(eventService::createEvent)
                      .flatMap(event -> Mono.just(modelMapper.map(event, EventResponseDTO.class)))
                      .flatMap(event -> ServerResponse.ok().bodyValue(event));
    }

    public Mono<ServerResponse> findEvents(ServerRequest request) {

        LocalDate defaultFromDate = LocalDate.now();
        LocalDate defaultToDate = defaultFromDate.withDayOfMonth(defaultFromDate.lengthOfMonth());

        return eventService.findEvents(request.queryParam("fromDate").map(LocalDate::parse).orElse(defaultFromDate),
                                       request.queryParam("toDate").map(LocalDate::parse).orElse(defaultToDate))
                .flatMap(event -> Mono.just(modelMapper.map(event, EventResponseDTO.class)))
                .collectList()
                .flatMap(event -> ServerResponse.ok().bodyValue(event));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {

        return eventService.delete(Long.valueOf(request.pathVariable("id")))
                .flatMap(event -> Mono.just(modelMapper.map(event, EventResponseDTO.class)))
                .flatMap(event -> ServerResponse.ok().build());
    }
}
