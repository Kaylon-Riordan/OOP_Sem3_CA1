import java.util.ArrayList;
public class Formulae {
    public float getMean(ArrayList<Float> nums) {
        float total = 0;
        int count = 0;
        for(float i: nums) {
            total += i;
            count++;
        }
        return total/count;
    }
}