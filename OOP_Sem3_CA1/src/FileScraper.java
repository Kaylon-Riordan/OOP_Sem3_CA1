import java.io.BufferedReader;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileReader;

public class FileScraper {
    
    // Symbol which separates entries in CSV. Same for all FileScrapers.
    private static String Delimiter = ",";

    // List of activities generated from scraping.
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    // Code relating to taking in data.
    public void ScrapeData(String fileName) 
    {
        String currentDirectory = getCsvDirectory(fileName);
        BufferedReader br = initReader(currentDirectory);
        ArrayList<String[]> rows = iterateRows(br);

        // For each row, construct an activity and add to arraylist.
        for (String[] row : rows) {
            activities.add(constructActivity(row));
        }
    }
    
    // Construct an activity object from given row.
    private Activity constructActivity(String[] row) {
        Activity activity = new Activity(row[0], row[1], row[2], row[3], row[4]);
        return activity;
    }

    // Read each row of CSV and return array list of strings delimited by columns.
    private ArrayList<String[]> iterateRows(BufferedReader br) 
    {
        // https://attacomsian.com/blog/java-read-parse-csv-file

        // Line being read currently
        String currentLine;

        // Represents data of each column. 5 columns in our CSV.
        String[] columnData;
        
        // Represents each row. Number of rows is unknowable, hence stored as arraylist.
        ArrayList<String[]> rows = new ArrayList<String[]>();

        try 
        {
            // Iterate first line so we can ignore it
            br.readLine();

            // Assigns currentline to a new value each time boolean is tested
            // Continues reading while rows are not empty.
            while ((currentLine = br.readLine()) != null) 
            {
                columnData = currentLine.split(Delimiter);
                
                // Remove excess spaces.
                for(int i = 0; i < columnData.length; ++i) 
                {
                    columnData[i] = columnData[i].trim();
                }

                rows.add(columnData);
            }
        } 
        catch (IOException e) 
        { // Catch any IOException
            e.printStackTrace();
        }
        
        return rows;
    }

    // Initilise reader given directory of csv.
    private BufferedReader initReader(String directory) 
    {
        // https://attacomsian.com/blog/java-read-parse-csv-file
        // https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-text-file
        try {
            return new BufferedReader(new FileReader(directory));
        } catch (IOException e) { // Catch any IOexception. Required to function.
            e.printStackTrace();
            return null;
        }
    }

    // Gets directory of CSV file given filename.
    // CSV is assumed to be in same directory of script.
    private String getCsvDirectory(String fileName) 
    {
         // https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
        String directory = System.getProperty("user.dir") + "\\csv\\" + fileName + ".csv";
        return directory;
    }

    public ArrayList<Activity> getActivities() 
    {
        return this.activities;
    }

    // https://attacomsian.com/blog/java-read-parse-csv-file
}
