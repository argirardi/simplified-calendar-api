package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import reactor.core.publisher.Mono;

public interface EventService {

    Mono<Event> createEvent(Event event);
}
