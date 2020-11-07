package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.repository;

import girardi.reis.anderson.simplifiedcalendarapi.business.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
