package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalErrorHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        if (throwable instanceof InvalidEventException) {
            return handle(serverWebExchange, (InvalidEventException) throwable);
        }

        return null;
    }

    public Mono<Void> handle(ServerWebExchange serverWebExchange, InvalidEventException invalidEventException) {

        Mono<DataBuffer> db = Mono.just(invalidEventException.getErrors()).map(errorsResponse -> {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsBytes(errorsResponse);
            } catch (JsonProcessingException e) {
                return e.getMessage().getBytes();
            }
        }).map(s -> serverWebExchange.getResponse().bufferFactory().wrap(s));

        serverWebExchange.getResponse().getHeaders().add("Content-Type", "application/json");
        serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return serverWebExchange.getResponse().writeWith(db);

    }
}

