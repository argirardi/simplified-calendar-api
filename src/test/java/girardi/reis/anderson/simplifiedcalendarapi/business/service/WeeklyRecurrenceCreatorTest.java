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
@SpringBootTest
public class WeeklyRecurrenceCreatorTest {

    @MockBean
    private EventRepositoryCustom eventRepositoryCustom;

    @Autowired
    private ModelMapper modelMapper;

    private RecurrenceCreator monthlyRecurrenceCreator;

    @BeforeEach
    public void setup(){
        this.monthlyRecurrenceCreator = new WeeklyRecurrenceCreator(eventRepositoryCustom, modelMapper);
    }

    @Test
    public void givenNumberOfRecurrencesIsThreeThenMustCreateEightRecurrentEvents() {

        Mockito.when(eventRepositoryCustom.save(SECOND_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SECOND_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(THIRD_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(THIRD_WEEKLY_RECURRING_EVENT));

        Mockito.when(eventRepositoryCustom.save(FOURTH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(FOURTH_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(FIFTH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(FIFTH_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(SIXTH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SIXTH_WEEKLY_RECURRING_EVENT));

        Mockito.when(eventRepositoryCustom.save(SEVENTH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SEVENTH_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(EIGHTH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(EIGHTH_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(NINETIETH_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(NINETIETH_WEEKLY_RECURRING_EVENT));

        StepVerifier.create(monthlyRecurrenceCreator.create(ORIGINAL_WEEKLY_RECURRING_EVENT))
                .expectNext(ORIGINAL_WEEKLY_RECURRING_EVENT)
                .expectComplete()
                .verify();

    }

    @Test
    public void givenEndRecurrenceDateIsFifteenDaysAfterStartThenMustCreateSixRecurrentEvents() {

        Mockito.when(eventRepositoryCustom.save(SECOND_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SECOND_DATED_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(THIRD_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(THIRD_DATED_WEEKLY_RECURRING_EVENT));

        Mockito.when(eventRepositoryCustom.save(FOURTH_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(FOURTH_DATED_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(FIFTH_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(FIFTH_DATED_WEEKLY_RECURRING_EVENT));
        Mockito.when(eventRepositoryCustom.save(SIXTH_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SIXTH_DATED_WEEKLY_RECURRING_EVENT));

        Mockito.when(eventRepositoryCustom.save(SEVENTH_DATED_WEEKLY_RECURRING_EVENT)).thenReturn(Mono.just(SEVENTH_DATED_WEEKLY_RECURRING_EVENT));

        StepVerifier.create(monthlyRecurrenceCreator.create(ORIGINAL_DATED_WEEKLY_RECURRING_EVENT))
                .expectNext(ORIGINAL_DATED_WEEKLY_RECURRING_EVENT)
                .expectComplete()
                .verify();
    }

    @Test
    public void givenRecurrenceIsInfiniteThenMustNotCreateRecurrence() {

        StepVerifier.create(monthlyRecurrenceCreator.create(ORIGINAL_WEEKLY_ENDLESS_RECURRING_EVENT))
                .expectNext(ORIGINAL_WEEKLY_ENDLESS_RECURRING_EVENT)
                .expectComplete()
                .verify();

    }
}
