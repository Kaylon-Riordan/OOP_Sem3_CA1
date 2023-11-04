import java.util.ArrayList;
public class MainApp {
    public static void main(String[] args) {
        FileScraper fs = new FileScraper();

        fs.ScrapeData("activity_data_10");
        fs.ScrapeData("activity_data_50");
        fs.ScrapeData("activity_data_100");

        ArrayList<Float> distances = new ArrayList<>();

        for (Activity activity : fs.getActivities()) {
            System.out.println(activity.getIntensity()+"\n"+activity.getCaloriesBurnt());
            distances.add(activity.getDistance());
        }

        distances.forEach((n) -> {
            System.out.println(n);
        });
    }
}
