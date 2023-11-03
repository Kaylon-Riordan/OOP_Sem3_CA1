import java.util.Scanner;
import java.lang.runtime.SwitchBootstraps;
import java.util.HashMap;
import java.util.Comparator;

public class Menu {
    private static Scanner kb = new Scanner(System.in);
    private static int choice = -1;
    private static String input = "";
    private static HashMap<String, String> menuText = new HashMap<>();

    enum InputType {
        CHOICE, TEXT, NONE
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
        {VeryLight, Light, blah, blah, blah}     
        """);

        menuText.put("DurationFilter",
        """
        Write a value for minimum duration.
        """);
    }

    public static void display(String menuName, InputType inputType) {
        boolean firstLoop = true;
        switch (inputType) {
            case CHOICE:
                do {
                    System.out.println(menuName);
                    System.out.println("-----");
                    System.out.println(menuText.get(menuName));

                    takeChoice();
                    if (!firstLoop) System.out.println("Invalid input, try again.\n");
                    firstLoop = false;
                } while (choice < 1);

                // Convert choice to string to agree with method.
                MainApp.performAction(menuName, choice + "");
                break;

            case TEXT:
                do {
                    System.out.println(menuName);
                    System.out.println("-----");
                    System.out.println(menuText.get(menuName));
                    takeText();
                } while (input.equals(""));

                // Case insensitive cancel
                if(input.toLowerCase().equals("cancel")) {
                    System.out.println("\n"); // default block won't execute
                } else {
                    MainApp.performAction(menuName, input);
                }
                break;

            case NONE:
                System.out.println(menuName);
                System.out.println("-----");
                System.out.println(menuText.get(menuName));
                System.out.println("\n");
                break;
        }

        resetChoice();
        resetText();
    }

    // performs action according to user's choice and menu selected.

    private static void resetChoice() {
        choice = -1;
    }

    private static void resetText() {
        input = "";
    }

    // https://www.youtube.com/watch?v=25kUc_ammbw
    // Take input as string to prevent residual newLines from .nextInt
    private static void takeChoice() {
        try {
            System.out.print("Make a selection: ");

            // Convert string input to int
            choice = Integer.parseInt(kb.nextLine());
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid input! Try again. Make sure your input is an integer.");
        }
    }

    private static void takeText() {
        System.out.print("Make a text input: ");
        input = kb.nextLine();
    }
}
