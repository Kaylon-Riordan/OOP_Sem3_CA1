import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Activity implements Comparable<Activity> {
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

    public Intensity getIntensity() {
        switch(activityType) {
            case Running:
                return calculateIntensity(4f, 8f, 12f, 16f);
            case Swimming:
                return calculateIntensity(0.5f, 1.25f, 2f, 2.75f);
            default: // Must be cycling
                return calculateIntensity(8f, 16f, 25f, 33f);
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
                + distance + ", avgHeartRate=" + avgHeartRate + "]";
    }

    // Sort by activity type by default
    @Override
    public int compareTo(Activity activity) {
        return getDate().compareTo(activity.getDate());
    }

    // https://www.w3schools.blog/comparable-comparator-java
    // https://www.java67.com/2019/06/top-5-sorting-examples-of-comparator-and-comparable-in-java.html#:~:text=For%20example%2C%20by%20using%20the,more%20realistic%20but%20complex%20ordering.
    // Comparators
    
    // By method reference
    // https://www.java67.com/2019/06/top-5-sorting-examples-of-comparator-and-comparable-in-java.html#:~:text=For%20example%2C%20by%20using%20the,more%20realistic%20but%20complex%20ordering.
    public static Comparator<Activity> byType = Comparator.comparing(Activity::getActivityType);
    public static Comparator<Activity> byDate = Comparator.comparing(Activity::getDate);
    public static Comparator<Activity> byDuration = Comparator.comparing(Activity::getDuration);
    public static Comparator<Activity> byDistance = Comparator.comparing(Activity::getDistance);
    public static Comparator<Activity> byCaloriesBurnt = Comparator.comparing(Activity::getCaloriesBurnt);
    // Awaiting formulae implementation. public static Comparator<Activity> byCaloriesBurnt = Comparator.comparing(Activity::calculateCaloriesBurnt);
}
