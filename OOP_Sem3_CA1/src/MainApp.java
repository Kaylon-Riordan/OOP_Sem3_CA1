public class MainApp {
    public static void main(String[] args) {
        FileScraper fs = new FileScraper();

        fs.ScrapeData("activity_data_10");

        for (Activity activity : fs.getActivities()) {
            System.out.println(activity.getActivityType().toString() + "\n" + activity.getDate().toString() + "\n" + activity.getDuration());
        }
    }
}
