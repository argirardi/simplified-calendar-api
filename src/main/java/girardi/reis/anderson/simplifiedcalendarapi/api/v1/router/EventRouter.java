package girardi.reis.anderson.simplifiedcalendarapi.api.v1.router;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.handler.EventHandler;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class EventRouter {

    @Bean
    public RouterFunction<ServerResponse> eventRouterFunction(EventHandler eventHandler) {
        return             route().POST("/api/v1/events"        , accept(APPLICATION_JSON), eventHandler::createEvent   , ops -> ops.beanClass(EventService.class).beanMethod("createEvent")).build()
                      .and(route().GET("/api/v1/events"         , accept(APPLICATION_JSON), eventHandler::findEvents    , ops -> ops.beanClass(EventService.class).beanMethod("findEvents")).build())
                      .and(route().DELETE("/api/v1/events/{id}" , accept(APPLICATION_JSON), eventHandler::delete        , ops -> ops.beanClass(EventService.class).beanMethod("delete")).build());
    }
}
