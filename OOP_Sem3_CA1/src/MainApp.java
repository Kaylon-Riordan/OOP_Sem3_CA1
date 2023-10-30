public class MainApp{
    public static void main(String[] args) {
        Activity act = new Activity("Running","04/01/2020","5","3","4");

        System.out.println(act.getActivityType());
        System.out.println(act.getDate());
        System.out.println(act.getDuration());
        System.out.println(act.getDistance());
        System.out.println(act.getAvgHeartRate());
    }
}
