package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventRequestDTO;
import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventResponseDTO;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.converter.EventEntityToEventConverter;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.converter.EventToEventEntityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.createTypeMap(EventRequestDTO.class, Event.class);
    modelMapper.createTypeMap(Event.class, EventResponseDTO.class);
    modelMapper.addConverter(new EventEntityToEventConverter());
    modelMapper.addConverter(new EventToEventEntityConverter());
    return modelMapper;
  }
}
