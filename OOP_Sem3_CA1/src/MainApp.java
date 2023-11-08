import java.util.ArrayList;
import java.util.Collections;

// For searching
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainApp {
    static ArrayList<Activity> activities;
    static FilePrinter fp = new FilePrinter();
    static FileScraper fs = new FileScraper();
    static Filter filter = (a) -> true; // No filter is defined.
    static Formulae formulae = new Formulae();
    static boolean active = true;

    // Ensure the code is run in an IDE via 'Run Java' and not 'Run Code'
    public static void main(String[] args) {
        Menu.initMenus();
        
        // Default data
        fs.ScrapeData("activity_data_10");
        activities = fs.getActivities();

        while(active)
        Menu.takeInputAndDisplay("Navigation", Menu.InputType.CHOICE);
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
                    case 1 -> Menu.takeInputAndDisplay("GetData", Menu.InputType.TEXT);
                    case 2 -> fp.tabulateActivity(activities);
                    case 3 -> Menu.takeInputAndDisplay("SortData", Menu.InputType.CHOICE);
                    case 4 -> Menu.takeInputAndDisplay("FilterData", Menu.InputType.CHOICE);
                    case 5 -> Menu.takeInputAndDisplay("OverallPerformance", Menu.InputType.NONE);
                    case 6 -> Menu.takeInputAndDisplay("Search", Menu.InputType.TEXT);
                    case 7 -> active = false;
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
                    case 2 -> {Collections.sort(activities); break;}
                    case 3 -> {Collections.sort(activities, Activity.byDuration); break;}
                    case 4 -> {Collections.sort(activities, Activity.byDistance); break;}
                    case 5 -> {Collections.sort(activities, Activity.byCaloriesBurnt); break;}
                    default -> {break outerswitch;}
                }

                Menu.takeInputAndDisplay("SortOrder", Menu.InputType.CHOICE);

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
                    case 1 -> Menu.takeInputAndDisplay("TypeFilter", Menu.InputType.TEXT);
                    case 2 -> Menu.takeInputAndDisplay("DistanceFilter", Menu.InputType.FLOAT);
                    case 3 -> Menu.takeInputAndDisplay("EnergyFilter", Menu.InputType.TEXT);
                    case 4 -> Menu.takeInputAndDisplay("DurationFilter", Menu.InputType.FLOAT);
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
            case "Search":
                printSearchResults(input);
        }
    }

    private static void printSearchResults(String dateString) {
        int index = -1;

        // Get the activities and sort them in their natural order.
        // Done in a separate variable to preserve user's sorting preferences.
        ArrayList<Activity> activitiesNaturalOrder = activities;
        Collections.sort(activitiesNaturalOrder);

        // Get the dates from each activity sorted in naturalorder
        ArrayList<LocalDate> dates = formulae.getDates(activitiesNaturalOrder);
        
        // Get the index of an activity which a matching date.
        try {
            index = Collections.binarySearch(dates, LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (Exception e) {
            System.out.println("Date input was invalid! Please follow the formatting.");
        }

        if(index > -1) {
            fp.tabulateActivity(activitiesNaturalOrder.get(index));
        } else {
            System.out.println("Entry not found.");
            System.out.println("\n");
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