package girardi.reis.anderson.simplifiedcalendarapi.business.model;

import girardi.reis.anderson.simplifiedcalendarapi.business.enumeration.FrequencyType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Set;

@Embeddable
public class Recurrence {

    @Enumerated(EnumType.STRING)
    @Column(name = "FREQUENCY_TYPE")
    private FrequencyType frequencyType;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "recurrence_days_week", joinColumns = @JoinColumn(name = "recurrence_id"))
    @Column(name = "days_week")
    private Set<DayOfWeek> daysOfWeek;

    @Column(name = "END_RECURRENCE_DATE_TIME")
    private ZonedDateTime endDateTime;

    @Column(name = "NUMBER_OF_OCCURRENCES")
    private Integer numberOfOccurrencesUntilTheEnd;

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getNumberOfOccurrencesUntilTheEnd() {
        return numberOfOccurrencesUntilTheEnd;
    }

    public void setNumberOfOccurrencesUntilTheEnd(Integer numberOfOccurrencesUntilTheEnd) {
        this.numberOfOccurrencesUntilTheEnd = numberOfOccurrencesUntilTheEnd;
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
