package girardi.reis.anderson.simplifiedcalendarapi.business.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Cloneable {

    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private Short duration;
    private Long parentEventId;
    private Recurrence recurrence;

    public Event() {
    }

    private Event(Event event) {
        this.name = event.getName();
        this.startDateTime = event.startDateTime;
        this.duration = event.getDuration();
        this.parentEventId = event.getId();
        this.recurrence = event.getRecurrence();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    public Long getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(Long parentEventId) {
        this.parentEventId = parentEventId;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public Boolean isRecurrent() {
        return getRecurrence() != null && getRecurrence().getFrequencyType() != null;
    }

    public Boolean isInfiniteRecurrence() {
        return Objects.nonNull(getRecurrence()) && (Objects.isNull(getRecurrence().getNumberOfOccurrences()) && Objects.isNull(getRecurrence().getEndRecurrenceDate()));
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public Event clone() {
        return new Event(this) ;
    }
}
