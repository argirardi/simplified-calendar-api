package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(ModelMapperConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModelMapperConfigTest {

    @Autowired
    private  ModelMapper modelMapper;

    @Test
    public void convertEventEntityToEventTest() {

        EventEntity eventEntity = new EventEntity();

        eventEntity.setOccursOnFriday(Boolean.TRUE);

        Event event = modelMapper.map(eventEntity, Event.class);

        Assertions.assertTrue(!event.getRecurrence().getDaysOfWeek().isEmpty());
    }
}
