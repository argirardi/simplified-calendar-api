package girardi.reis.anderson.simplifiedcalendarapi.api.v1.dto;

import girardi.reis.anderson.simplifiedcalendarapi.api.v1.enumeration.FrequencyType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Set;

public class RecurrenceDTO {

    private FrequencyType frequencyType;
    private Set<DayOfWeek> daysOfWeek;
    private ZonedDateTime endDateTime;
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
