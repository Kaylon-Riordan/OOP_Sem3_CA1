import java.util.ArrayList;
public class MainApp {
    public static void main(String[] args) {
        FileScraper fs = new FileScraper();

        fs.ScrapeData("activity_data_10");
        fs.ScrapeData("activity_data_50");
        fs.ScrapeData("activity_data_100");

        ArrayList<Float> durations = new ArrayList<>();

        for (Activity activity : fs.getActivities()) {
            durations.add(activity.getDuration());
        }
        System.out.println(Formulae.mean());
    }
}
