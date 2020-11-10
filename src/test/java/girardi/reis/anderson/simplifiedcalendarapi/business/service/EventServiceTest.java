package girardi.reis.anderson.simplifiedcalendarapi.business.service;


import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import(ModelMapperConfig.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventServiceTest {

    @MockBean
    private EventRepositoryCustom eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DailyRecurrenceCreator dailyRecurrenceCreator;

    @Test
    public void test() {

        System.out.println(1);

    }


}
