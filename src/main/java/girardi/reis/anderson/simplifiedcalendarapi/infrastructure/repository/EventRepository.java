package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository;

import girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity.EventEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EventRepository extends ReactiveCrudRepository<EventEntity, Long> {
}
