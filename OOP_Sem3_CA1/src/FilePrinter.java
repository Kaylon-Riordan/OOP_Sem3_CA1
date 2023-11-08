// https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/#:~:text=We%20can%20use%20DecimalFormat(%220.00,set%20a%20specified%20rounding%20mode.
import java.text.DecimalFormat;

public class FilePrinter {

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    // Output
    private static final String Formatting = "%-10s %-12s %-10s %-10s %-10s %-10s %-10s";
    public static final String Headings = String.format(Formatting, "Type", "Date", "Duration", "Distance", "HeartRate", "Calories", "Intensity");

    // Output headers for each attribute of an activity.
    public void soutHeaders() {
        System.out.println(Headings);
    }

    // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
    public void tabulateActivity(Activity activity) {
        String output = String.format(Formatting, activity.getActivityType(), activity.getDate(), activity.getDuration(), decimalFormat.format(activity.getDistance()), activity.getAvgHeartRate(), decimalFormat.format(activity.getCaloriesBurnt()), activity.getIntensity());
        System.out.println(output);
    }
}