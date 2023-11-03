import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Activity {
    private ActivityType activityType;
    private LocalDate date;
    private float duration, distance, avgHeartRate;

    public Activity(String activityType, String date, String duration, String distance, String avgHeartRate) {
        this.activityType = ActivityType.valueOf(activityType);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duration = Float.parseFloat(duration);
        this.distance = Float.parseFloat(distance);
        this.avgHeartRate = Float.parseFloat(avgHeartRate);
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
        return avgHeartRate;
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
        this.avgHeartRate = avgHeartRate;
    }

    // https://www.w3schools.blog/comparable-comparator-java
    // https://www.java67.com/2019/06/top-5-sorting-examples-of-comparator-and-comparable-in-java.html#:~:text=For%20example%2C%20by%20using%20the,more%20realistic%20but%20complex%20ordering.
    // https://www.geeksforgeeks.org/collections-reverseorder-java-examples/
    // Comparators
    
    // By Lambda
    // Comparator<Activity> byType = (a1, a2) -> a1.getActivityType().compareTo(a2.getActivityType());
    
    // By method reference
    // https://www.java67.com/2019/06/top-5-sorting-examples-of-comparator-and-comparable-in-java.html#:~:text=For%20example%2C%20by%20using%20the,more%20realistic%20but%20complex%20ordering.
    public static Comparator<Activity> byType = Comparator.comparing(Activity::getActivityType);
    public static Comparator<Activity> byDate = Comparator.comparing(Activity::getDate);
    public static Comparator<Activity> byDuration = Comparator.comparing(Activity::getDuration);
    public static Comparator<Activity> byDistance = Comparator.comparing(Activity::getDistance);
    // Awaiting formulae implementation. public static Comparator<Activity> byCaloriesBurnt = Comparator.comparing(Activity::calculateCaloriesBurnt);
}
