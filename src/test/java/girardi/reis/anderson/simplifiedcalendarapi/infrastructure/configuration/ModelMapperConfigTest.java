package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration;

import girardi.reis.anderson.simplifiedcalendarapi.business.domain.Event;
import girardi.reis.anderson.simplifiedcalendarapi.business.domain.EventMock;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
public class ModelMapperConfigTest {

    @Autowired
    private  ModelMapper modelMapper;

    @Test
    public void givenNonRecurringDailyEventEntityThenMustConvertToNonRecurringDailyEvent() {
        Assertions.assertEquals(EventMock.NON_RECURRING_DAILY_EVENT, modelMapper.map(EventEntityMock.NON_RECURRING_DAILY_EVENT_ENTITY, Event.class));
    }

    @Test
    public void givenAllWeekDaysWeeklyEventEntityThenMustConvertToAllWeekDaysWeeklyEvent() {
        Assertions.assertEquals(EventMock.ALL_WEEK_DAYS_WEEKLY_RECURRING_EVENT, modelMapper.map(EventEntityMock.ALL_WEEK_DAYS_WEEKLY_RECURRING_EVENT_ENTITY, Event.class));
    }



}
