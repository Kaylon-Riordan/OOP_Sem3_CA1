import java.util.Scanner;
import java.util.HashMap;

public class Menu {
    private static Scanner kb = new Scanner(System.in);
    private static int choice = -1;
    private static String input = "";
    private static HashMap<String, String> menuText = new HashMap<>();
    private static float inputFloat = -1;
    

    enum InputType {
        CHOICE, TEXT, FLOAT, NONE
    }

    public static void initMenus() {
        // Name of menu, followed by its content
        menuText.put("Navigation", 
        """
        1) Get Activities from CSV
        2) View Activities in a Table
        3) Sort Activities by Category
        4) Filter Activities
        5) View Overall Performance
        6) Search by Date
        7) Exit
        """);
        
        menuText.put("GetData", 
        """
        Type the filename of your activity csv.
        Type 'cancel' to return.
        """);

        menuText.put("SortData",
        """
        Choose a category to sort your data by:
        1) Type
        2) Date
        3) Duration
        4) Distance
        5) Calories Burnt
        6) Cancel
        """);

        menuText.put("SortOrder",
        """
        Choose what order to sort by:
        1) Ascending
        2) Descending
        """);

        menuText.put("FilterData",
        """
        Choose what to filter your data by:
        1) Type
        2) Minimum Distance
        3) Type of Energy Expended
        4) Minimum Duration
        5) Remove Filter
        6) Cancel
        """);

        menuText.put("TypeFilter",
        """
        Write the name of the activity.
        {Running, Swimming, Cycling}

        Type 'cancel' to return.
        """);

        menuText.put("DistanceFilter",
        """
        Write a value for minimum distance.        
        """);

        menuText.put("EnergyFilter",
        """
        Write the intensity of the activity.
        {VeryLight, Light, Moderate, Vigorous, VeryVigourous}     
        """);

        menuText.put("DurationFilter",
        """
        Write a value for minimum duration.
        """);

        menuText.put("OverallPerformance",
        """
        Here is your average performance values:        
        """);

        menuText.put("Search",
        """
        Type a date in the format yyyy-mm-dd
        E.g 11th of January 2020 would be 2020-01-11

        Type 'cancel' to exit.
        """);
    }

    private static void printMenu(String menuName) {
        System.out.println(menuName);
        System.out.println("-----");
        System.out.println(menuText.get(menuName));
    }

    public static void takeInputAndDisplay(String menuName, InputType inputType) {

        switch (inputType) {
            case CHOICE:
                do {
                    printMenu(menuName);
                    takeChoice();
                } while (choice < 1);

                // Convert choice to string to agree with method.
                MainApp.performAction(menuName, choice + "");
                resetChoice();
                break;

            case TEXT:
                do {
                    printMenu(menuName);
                    takeText();
                } while (input.equals(""));

                // Case insensitive cancel
                if(input.toLowerCase().equals("cancel")) {
                    System.out.println("\n"); // default block won't execute
                } else {
                    MainApp.performAction(menuName, input);
                }

                resetText();
                break;

            case FLOAT:
                do {
                    printMenu(menuName);
                    takeFloat();
                } while (inputFloat < 0);

                // Convert float to string to agree with method.
                MainApp.performAction(menuName, inputFloat + "");
                resetFloat();
                break;


            case NONE:
                printMenu(menuName);
                MainApp.performAction(menuName, input);
                System.out.println("\n");
                break;
        }
        
        System.out.println();
    }

    // performs action according to user's choice and menu selected.

    private static void resetChoice() {
        choice = -1;
    }

    private static void resetText() {
        input = "";
    }

    private static void resetFloat() {
        inputFloat = -1;
    }

    // https://www.youtube.com/watch?v=25kUc_ammbw
    // Take input as string to prevent residual newLines from .nextInt
    private static void takeChoice() {
        try {
            System.out.print("Make a selection: ");

            // Convert string input to int
            choice = Integer.parseInt(kb.nextLine());
            System.out.println("");// New line
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid input! Try again. Make sure your input is an integer.\n");
        }
    }

    // Take input as string to prevent residual newLines from .nextInt
    private static void takeFloat() {
        try {
            System.out.print("Make a float input: ");

            input = kb.nextLine();
            System.out.println("");// New line

            // Convert string input to int
            inputFloat = Float.parseFloat(input);
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid input! Try again. Make sure your input is a float.\n");
        }
    }

    private static void takeText() {
        System.out.print("Make a text input: ");
        input = kb.nextLine();
        System.out.println("");// New line
    }
}
