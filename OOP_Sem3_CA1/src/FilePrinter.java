// https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/#:~:text=We%20can%20use%20DecimalFormat(%220.00,set%20a%20specified%20rounding%20mode.
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FilePrinter {

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    // Output
    private static final String Formatting = "%-10s %-12s %-10s %-10s %-10s %-10s %-10s";
    public static final String Headings = String.format(Formatting, "Type", "Date", "Duration", "Distance", "HeartRate", "Calories", "Intensity");

    // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
    public void tabulateActivity(Activity activity) {
        System.out.println(Headings);
        String output = String.format(Formatting, activity.getActivityType(), activity.getDate(), activity.getDuration(), decimalFormat.format(activity.getDistance()), activity.getAvgHeartRate(), decimalFormat.format(activity.getCaloriesBurnt()), activity.getIntensity());
        System.out.println(output + "\n");
    }

    public void tabulateActivity(Activity activity, boolean printMany) {
        if(!printMany) System.out.println(Headings);
        
        String output = String.format(Formatting, activity.getActivityType(), activity.getDate(), activity.getDuration(), decimalFormat.format(activity.getDistance()), activity.getAvgHeartRate(), decimalFormat.format(activity.getCaloriesBurnt()), activity.getIntensity());
        System.out.println(output);

        if(!printMany) System.out.println("\n");
    }

        for (Activity activity : activities) {
            if(MainApp.filter.filter(activity))
            tabulateActivity(activity, true);
        }
    }
    
}