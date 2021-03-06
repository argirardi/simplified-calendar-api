package girardi.reis.anderson.simplifiedcalendarapi.business.domain;

import girardi.reis.anderson.simplifiedcalendarapi.business.enumeration.FrequencyType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class Recurrence {

    private FrequencyType frequencyType;
    private Set<DayOfWeek> daysOfWeek;
    private LocalDate endRecurrenceDate;
    private Integer numberOfOccurrences;

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
