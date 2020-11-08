package girardi.reis.anderson.simplifiedcalendarapi.infrastructure.entity;

import girardi.reis.anderson.simplifiedcalendarapi.business.enumeration.FrequencyType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Table("event")
public class EventEntity {

    @Id
    private Long id;
    private String name;
    private ZonedDateTime startDateTime;
    private Short duration;
    private Long parentEventId;

    private FrequencyType frequencyType;
    private LocalDate endRecurrenceDate;
    private Integer numberOfOccurrences;

    private Boolean occursOnSunday = Boolean.FALSE;
    private Boolean occursOnMonday = Boolean.FALSE;
    private Boolean occursOnTuesday = Boolean.FALSE;
    private Boolean occursOnWednesday = Boolean.FALSE;
    private Boolean occursOnThursday = Boolean.FALSE;
    private Boolean occursOnFriday = Boolean.FALSE;
    private Boolean occursOnSaturday = Boolean.FALSE;

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

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
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

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public LocalDate getEndRecurrenceDate() {
        return endRecurrenceDate;
    }

    public void setEndRecurrenceDate(LocalDate endRecurrenceDate) {
        this.endRecurrenceDate = endRecurrenceDate;
    }

    public Integer getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public void setNumberOfOccurrences(Integer numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public Boolean isOccursOnSunday() {
        return occursOnSunday;
    }

    public void setOccursOnSunday(Boolean occursOnSunday) {
        this.occursOnSunday = occursOnSunday;
    }

    public Boolean isOccursOnMonday() {
        return occursOnMonday;
    }

    public void setOccursOnMonday(Boolean occursOnMonday) {
        this.occursOnMonday = occursOnMonday;
    }

    public Boolean isOccursOnTuesday() {
        return occursOnTuesday;
    }

    public void setOccursOnTuesday(Boolean occursOnTuesday) {
        this.occursOnTuesday = occursOnTuesday;
    }

    public Boolean isOccursOnWednesday() {
        return occursOnWednesday;
    }

    public void setOccursOnWednesday(Boolean occursOnWednesday) {
        this.occursOnWednesday = occursOnWednesday;
    }

    public Boolean isOccursOnThursday() {
        return occursOnThursday;
    }

    public void setOccursOnThursday(Boolean occursOnThursday) {
        this.occursOnThursday = occursOnThursday;
    }

    public Boolean isOccursOnFriday() {
        return occursOnFriday;
    }

    public void setOccursOnFriday(Boolean occursOnFriday) {
        this.occursOnFriday = occursOnFriday;
    }

    public Boolean isOccursOnSaturday() {
        return occursOnSaturday;
    }

    public void setOccursOnSaturday(Boolean occursOnSaturday) {
        this.occursOnSaturday = occursOnSaturday;
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


}
