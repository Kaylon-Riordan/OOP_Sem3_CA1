import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity {
    private ActivityType activityType;
    private LocalDate date;
    private float duration;
    private float distance;
    private float AvgHeartRate;

    public Activity(String activityType, String date, String duration, String distance, String avgHeartRate) {
        this.activityType = ActivityType.valueOf(activityType);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duration = Float.parseFloat(duration);
        this.distance = Float.parseFloat(distance);
        this.AvgHeartRate = Float.parseFloat(avgHeartRate);
    }

    public ActivityType getActivityType() {
        return activityType;
    }
    public LocalDate getDate() {
        return date;
    }
    public float getDuration() {
        return duration;
    }
    public float getDistance() {
        return distance;
    }
    public float getAvgHeartRate() {
        return AvgHeartRate;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public void setAvgHeartRate(float avgHeartRate) {
        AvgHeartRate = avgHeartRate;
    }

    public Intensity getIntensity() {
        float kmph = distance/(duration/60);

        switch(activityType) {
            case Running:
                return calculateIntensity(4f, 8f, 12f, 16f);
            case Swimming:
                return calculateIntensity(0.5f, 1.25f, 2f, 2.75f);
            default:
                return calculateIntensity(8f, 16f, 25f, 33f);
        }
    }

    private Intensity calculateIntensity(float f1, float f2, float f3, float f4) {
        float speed = distance/(duration/60);
        if (speed < f1) {
            return Intensity.VeryLight;
        }
        else if (speed < f2) {
            return Intensity.Light;
        }
        else if (speed < f3) {
            return Intensity.Moderate;
        }
        else if (speed < f4) {
            return Intensity.Vigorous;
        }
        else {
            return Intensity.VeryVigorous;
        }
    }

    public float getCaloriesBurnt() {
        switch(activityType) {
            case Running:
                return calculateCalories(4.1f, 7.2f, 10f, 15.4f, 20.8f)*duration;
            case Swimming:
                return calculateCalories(5f, 6.3f, 7.6f, 8.9f, 10.2f)*duration;
            default:
                return calculateCalories(2f, 5f, 7f, 13f, 15f)*duration;
        }
    }

    private float calculateCalories(float f1, float f2, float f3, float f4, float f5) {
        switch(getIntensity()) {
            case VeryLight:
                return f1;
            case Light:
                return f2;
            case Moderate:
                return f3;
            case Vigorous:
                return f4;
            default:
                return f5;
        }
    }

    @Override
    public String toString() {
        return "Activity [activityType=" + activityType + ", date=" + date + ", duration=" + duration + ", distance="
                + distance + ", AvgHeartRate=" + AvgHeartRate + "]";
    }
}
