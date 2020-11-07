package girardi.reis.anderson.simplifiedcalendarapi.business.enumeration;

public enum FrequencyType {

    DAILY("dailyRecurrenceCreator"),
    WEEKLY("weeklyRecurrenceCreator"),
    MONTHLY("monthlyRecurrenceCreator");

    private String creatorClassName;

    FrequencyType(String creator) {
        this.creatorClassName = creator;
    }

    public String getCreatorClassName() {
        return creatorClassName;
    }
}
