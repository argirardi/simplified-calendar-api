package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto.EventDTO;
import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.createTypeMap(EventDTO.class, Event.class);
    modelMapper.createTypeMap(Event.class, Event.class);
    return modelMapper;
  }
}
