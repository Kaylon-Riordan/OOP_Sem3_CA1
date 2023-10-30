import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity {
    private ActivityType activityType;
    private LocalDate date;
    private float duration, distance, AvgHeartRate;

    public Activity(String activityType, String date, String duration, String distance, String avgHeartRate) {
        ActivityType.valueOf(activityType);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.S"));
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
}