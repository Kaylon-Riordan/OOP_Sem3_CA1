import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class MainApp {
    static Comparator<Activity> chosenSort = null;
    static ArrayList<Activity> activities;
    static FilePrinter fp = new FilePrinter();

    public static void main(String[] args) {
        FileScraper fs = new FileScraper();
        Menu.initMenus();

        fs.ScrapeData("activity_data_10");
        fs.ScrapeData("activity_data_50");

        // Hashset populated. Convert to ArrayList in order to sort.
        activities = fs.getActivities();

        while(true)
        Menu.display("Navigation", Menu.InputType.CHOICE);
    }

    private static void viewTable() {
        System.out.println(FilePrinter.Headings);
        for (Activity activity : activities) {
            fp.tabulateActivity(activity);
        }
        System.out.println("\n");
    }

    public static void performAction(String menuName, String input) {
        switch (menuName) {
            // ------------------------
            case "Navigation":
                switch (Integer.parseInt(input)) {
                    case 1 -> Menu.display("GetData", Menu.InputType.TEXT);
                    case 2 -> viewTable();
                    case 3 -> Menu.display("SortData", Menu.InputType.CHOICE);
                    case 4 -> Menu.display("FilterData", Menu.InputType.CHOICE);
                    case 5 -> Menu.display("", Menu.InputType.NONE);
                }
                break;
            
            case "GetData":
                // Scrape data would go here.
                break;
            
            case "SortData":
                switch(Integer.parseInt(input)) {
                    case 1 -> Collections.sort(activities, Activity.byType);
                    case 2 -> Collections.sort(activities, Activity.byDate);
                    case 3 -> Collections.sort(activities, Activity.byDuration);
                    case 4 -> Collections.sort(activities, Activity.byDistance);
                    // case 5 -> chosenSort = Activity.byCaloriesBurnt;
                }

                Menu.display("SortOrder", Menu.InputType.CHOICE);
                break;
            
            case "SortOrder":
                switch (Integer.parseInt(input)) {
                    case 2:
                        // https://www.geeksforgeeks.org/collections-reverseorder-java-examples/
                        Collections.reverse(activities);
                        break;
                }
                break;
        }
    }
}