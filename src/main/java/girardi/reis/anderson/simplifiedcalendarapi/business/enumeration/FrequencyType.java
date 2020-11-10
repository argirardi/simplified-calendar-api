package girardi.reis.anderson.simplifiedcalendarapi.business.enumeration;

import girardi.reis.anderson.simplifiedcalendarapi.business.service.DailyRecurrenceCreator;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.MonthlyRecurrenceCreator;
import girardi.reis.anderson.simplifiedcalendarapi.business.service.WeeklyRecurrenceCreator;
import org.springframework.util.StringUtils;

public enum FrequencyType {

    DAILY(StringUtils.uncapitalize(DailyRecurrenceCreator.class.getSimpleName())),
    WEEKLY(StringUtils.uncapitalize(WeeklyRecurrenceCreator.class.getSimpleName())),
    MONTHLY(StringUtils.uncapitalize(MonthlyRecurrenceCreator.class.getSimpleName()));

    private String creatorClassName;

    FrequencyType(String creator) {
        this.creatorClassName = creator;
    }

    public String getCreatorClassName() {
        return creatorClassName;
    }
}
