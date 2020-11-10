package girardi.reis.anderson.simplifiedcalendarapi.business.service;

import girardi.reis.anderson.simplifiedcalendarapi.business.repository.EventRepositoryCustom;
import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.configuration.ModelMapperConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static girardi.reis.anderson.simplifiedcalendarapi.business.domain.EventMock.*;

@Import(ModelMapperConfig.class)
@ExtendWith(SpringExtension.class)
public class DailyRecurrenceCreatorTest {

    @MockBean
    private EventRepositoryCustom eventRepositoryCustom;

    @Autowired
    private ModelMapper modelMapper;

    private RecurrenceCreator dailyRecurrenceCreator;

    @BeforeEach
    public void setup(){
        this.dailyRecurrenceCreator = new DailyRecurrenceCreator(eventRepositoryCustom, modelMapper);
    }

    @Test
    public void givenNumberOfRecurrencesIsThreeThenMustCreateTwoRecurrentEvents() {

        Mockito.when(eventRepositoryCustom.save(FIRST_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(FIRST_DAILY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(SECOND_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(SECOND_DAILY_RECURRING_EVENT));

        StepVerifier.create(dailyRecurrenceCreator.create(ORIGINAL_DAILY_RECURRING_EVENT))
                .expectNext(ORIGINAL_DAILY_RECURRING_EVENT)
                .expectComplete()
                .verify();
    }

    @Test
    public void givenEndRecurrenceDateIsFiveDaysAfterStartThenMustCreateFourRecurrentEvents() {

        Mockito.when(eventRepositoryCustom.save(FIRST_DATED_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(FIRST_DATED_DAILY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(SECOND_DATED_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(SECOND_DATED_DAILY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(THIRD_DATED_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(THIRD_DATED_DAILY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(FOURTH_DATED_DAILY_RECURRING_EVENT)).thenReturn(Mono.just(FOURTH_DATED_DAILY_RECURRING_EVENT));

        StepVerifier.create(dailyRecurrenceCreator.create(ORIGINAL_DATED_DAILY_RECURRING_EVENT))
                .expectNext(ORIGINAL_DATED_DAILY_RECURRING_EVENT)
                .expectComplete()
                .verify();
    }

    @Test
    public void givenRecurrenceIsInfiniteThenMustNotCreateRecurrence() {

        StepVerifier.create(dailyRecurrenceCreator.create(ORIGINAL_DAILY_ENDLESS_RECURRING_EVENT))
                .expectNext(ORIGINAL_DAILY_ENDLESS_RECURRING_EVENT)
                .expectComplete()
                .verify();

    }
}
