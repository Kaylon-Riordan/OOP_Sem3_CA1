public class FilePrinter {

    public FilePrinter() {}

    // Output
    private static final String Formatting = "%-10s %-12s %-10s %-10s %-10s";
    public static final String Headings = String.format(Formatting, "Type", "Date", "Duration", "Distance", "Average Heart Rate");

    // Output headers for each attribute of an activity.
    public void soutHeaders() {
        System.out.println(Headings);
    }

    // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
    public void tabulateActivity(Activity activity) {
        String output = String.format(Formatting, activity.getActivityType(), activity.getDate(), activity.getDuration(), activity.getDistance(), activity.getAvgHeartRate());
        System.out.println(output);
    }
}
