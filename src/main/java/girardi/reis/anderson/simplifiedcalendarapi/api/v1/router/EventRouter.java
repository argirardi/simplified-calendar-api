package girardi.reis.anderson.simplifiedcalendarapi.api.v1.router;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventResponseDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.handler.EventHandler;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.core.Constants.OPERATION_ATTRIBUTE;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.operation.Builder.operationBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class EventRouter {

    @Bean
    public RouterFunction<ServerResponse> eventRouterFunction(EventHandler eventHandler) {
        return    route(POST("/api/v1/events"),  eventHandler::createEvent)
                             .withAttribute(OPERATION_ATTRIBUTE, operationBuilder().operationId("createEvent")
                             .requestBody(requestBodyBuilder().implementation(EventRequestDTO.class))
                             .response(responseBuilder().responseCode("201")))
                 .and(route(GET("/api/v1/events"), eventHandler::findEvents)
                             .withAttribute(OPERATION_ATTRIBUTE, operationBuilder().operationId("findEvents")
                             .parameter(parameterBuilder().name("fromDate").description("Start Date"))
                             .parameter(parameterBuilder().name("toDate").description("End Date"))
                             .response(responseBuilder().responseCode("200").implementationArray(EventResponseDTO.class))))
                 .and(route(DELETE("/api/v1/events/{id}"), eventHandler::delete)
                              .withAttribute(OPERATION_ATTRIBUTE, operationBuilder().operationId("delete")
                              .parameter(parameterBuilder().in(ParameterIn.PATH).name("id"))
                              .response(responseBuilder().responseCode("200"))));
    }
}