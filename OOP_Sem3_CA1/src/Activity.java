public class Activity {
    public Activity(String s0, String s1, String s2, String s3, String s4) {

<<<<<<< Updated upstream
=======
    public Activity(String activityType, String date, String duration, String distance, String avgHeartRate) {
        ActivityType.valueOf(activityType);
        //this.date = LocalDate.parse(date.substring(1));
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
>>>>>>> Stashed changes
    }

    @Override
    public String toString() {
        return "Activity [activityType=" + activityType + ", date=" + date + ", duration=" + duration + ", distance="
                + distance + ", AvgHeartRate=" + AvgHeartRate + "]";
    }
}
