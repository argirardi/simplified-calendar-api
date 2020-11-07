package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.validator;

import reactor.core.publisher.Mono;

public interface Validatable<T>{

    Mono<T> validate(T t);

}
