import java.util.ArrayList;
import java.util.Collections;

public class MainApp {
    static ArrayList<Activity> activities;
    static FilePrinter fp = new FilePrinter();
    static FileScraper fs = new FileScraper();
    private static Filter filter = (a) -> true; // No filter is defined.
    static Formulae formulae = new Formulae();
    static boolean active = true;

    // Ensure the code is run in an IDE via 'Run Java' and not 'Run Code'
    public static void main(String[] args) {
        Menu.initMenus();
        
        Menu.display("GetData", Menu.InputType.TEXT);
        while(active)
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

        // Used in enum menus
        String inputEnumFormatted;

        //https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
        // Using a label to break outer loops
        outerswitch:    
        switch (menuName) {
            // ------------------------
            case "Navigation":
                switch (Integer.parseInt(input)) {
                    case 1 -> Menu.display("GetData", Menu.InputType.TEXT);
                    case 2 -> viewTable();
                    case 3 -> Menu.display("SortData", Menu.InputType.CHOICE);
                    case 4 -> Menu.display("FilterData", Menu.InputType.CHOICE);
                    case 5 -> Menu.display("OverallPerformance", Menu.InputType.NONE);
                    case 6 -> active = false;
                }
                break;
            
            case "GetData":
                fs.ScrapeData(input);
                // Hashset populated. Convert to ArrayList in order to sort.
                activities = fs.getActivities();
                break;
            
            case "SortData":
                switch(Integer.parseInt(input)) {
                    case 1 -> {Collections.sort(activities, Activity.byType); break;}
                    case 2 -> {Collections.sort(activities, Activity.byDate); break;}
                    case 3 -> {Collections.sort(activities, Activity.byDuration); break;}
                    case 4 -> {Collections.sort(activities, Activity.byDistance); break;}
                    case 5 -> {Collections.sort(activities, Activity.byCaloriesBurnt); break;}
                    default -> {break outerswitch;}
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

            case "EnergyFilter":
                inputEnumFormatted = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                SetFilter(Intensity.valueOf(inputEnumFormatted));
                break;
            case "TypeFilter":
                inputEnumFormatted = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                SetFilter(ActivityType.valueOf(inputEnumFormatted));
                break;
            // Omitting a 'break' allows us to run the same code.
            case "DistanceFilter":
            case "DurationFilter":
                SetFilter(menuName, Float.parseFloat(input));
                break;
            case "OverallPerformance":
                System.out.println("Your distance average is: " + formulae.getMean(activities, "distance"));
                System.out.println("Your calories burnt average is: " + formulae.getMean(activities, "calories"));
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
    
    private static void SetFilter(ActivityType enumValue) {
        filter = (a) -> a.getActivityType() == enumValue;
    }

    private static void SetFilter(Intensity enumValue) {
        filter = (a) -> a.getIntensity() == enumValue;
    }
}