import java.time.LocalDate;
import java.util.ArrayList;
public class Formulae {

    public ArrayList<LocalDate> getDates(ArrayList<Activity> activities) {
        ArrayList<LocalDate> dates = new ArrayList<>();

        for (Activity activity: activities) {
            dates.add(activity.getDate());
        }

        return dates;
    }

    public ArrayList<Float> getNums(ArrayList<Activity> activities, String attribute) {
        
        ArrayList<Float> floats = new ArrayList<>();
    
        switch (attribute) {
            case "distance":
                for (Activity activity : activities) {
                    floats.add(activity.getDistance());
                }
                break;
            
            case "calories":
                for (Activity activity : activities) {
                    floats.add(activity.getCaloriesBurnt());
                }
                break;
        }

        return floats;
    }
    

    public float getMean(ArrayList<Activity> activities, String attribute) {
        ArrayList<Float> nums = getNums(activities, attribute);
        float total = 0;
        for(float i: nums) {
            total += i;
        }
        return total/nums.size();
    }

}