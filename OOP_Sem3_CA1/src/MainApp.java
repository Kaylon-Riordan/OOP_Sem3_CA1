import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class MainApp {
    static Comparator<Activity> chosenSort = null;
    static ArrayList<Activity> activities;
    static FilePrinter fp = new FilePrinter();
    static FileScraper fs = new FileScraper();
    private static Filter filter = (a) -> true; // No filter is defined.

    public static void main(String[] args) {
        Menu.initMenus();
        //Menu.display("GetData", Menu.InputType.TEXT);
        
        Menu.display("GetData", Menu.InputType.TEXT);

        while(true)
        Menu.display("Navigation", Menu.InputType.CHOICE);
    }

    private static void viewTable() {
        System.out.println(FilePrinter.Headings);
        for (Activity activity : activities) {
            // If the activity meets the filter criteria
            if(filter.filter(activity))
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
                fs.ScrapeData(input);
                // Hashset populated. Convert to ArrayList in order to sort.
                activities = fs.getActivities();
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
            case "FilterData":
                switch (Integer.parseInt(input)) {
                    case 1 -> Menu.display("TypeFilter", Menu.InputType.TEXT);
                    case 2 -> Menu.display("DistanceFilter", Menu.InputType.FLOAT);
                    case 3 -> Menu.display("EnergyFilter", Menu.InputType.TEXT);
                    case 4 -> Menu.display("DurationFilter", Menu.InputType.FLOAT);
                    case 5 -> filter = (a) -> true;
                }
                break;

            // Uncomment once merged with Kaylon's intensity enum.
            // Omitting a 'break' allows us to run the same code.
            // case "EnergyFilter":
            case "TypeFilter":
                // Verifies that an activity's enum matches the user's input. User's input is converted to title case.
                String inputEnumFormatted = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                SetFilter(menuName, ActivityType.valueOf(inputEnumFormatted));
                break;
            // The code is the same in both cases.
            case "DistanceFilter":
            case "DurationFilter":
                SetFilter(menuName, Float.parseFloat(input));
                break;
        }
    }

    private static void SetFilter(String menuName, float minValue) {
        System.out.println(minValue);
        if(menuName.equals("DistanceFilter")) {
            
            filter = (a) -> a.getDistance() > minValue;
        } else { // Menu must be duration.
            filter = (a) -> a.getDuration() > minValue;
        }
    }
    
    private static void SetFilter(String menuName, ActivityType enumValue) {
        if(menuName.equals("TypeFilter")) {
            filter = (a) -> a.getActivityType() == enumValue;
        }
        // else { // It must be referring to energy. Add upon merge.

        // }
    }
}