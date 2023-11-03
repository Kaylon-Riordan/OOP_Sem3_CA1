public class Formulae {
    public static float mean(float... nums) {
        float total = 0;
        int count = 0;
        for (float i : nums) {
            total += i;
            count++;
        }
        return total/count;
    }

    public static Intensity getIntensity(ActivityType activity, float duration, float distance) {
        float kmph = distance/(duration/60);

        switch(activity) {
            case Running:
                return calculateIntensity(4f, 8f, 12f, 16f, kmph);
            case Swimming:
                return calculateIntensity(0.5f, 1.25f, 2f, 2.75f, kmph);
            default:
                return calculateIntensity(8f, 16f, 25f, 33f, kmph);
        }
    }

    private static Intensity calculateIntensity(float f1, float f2, float f3, float f4, float kmph) {
        if (kmph < f1) {
            return Intensity.VeryLight;
        }
        else if (kmph < f2) {
            return Intensity.Light;
        }
        else if (kmph < f3) {
            return Intensity.Moderate;
        }
        else if (kmph < f4) {
            return Intensity.Vigorous;
        }
        else {
            return Intensity.VeryVigorous;
        }
    }

    public static float caloriesBurnt(ActivityType activity, Intensity intensity, float duration) {
        switch(activity) {
            case Running:
                return calculateCalories(4.1f, 7.2f, 10f, 15.4f, 20.8f, intensity)*duration;
            case Swimming:
                return calculateCalories(5f, 6.3f, 7.6f, 8.9f, 10.2f, intensity)*duration;
            default:
                return calculateCalories(2f, 5f, 7f, 13f, 15f, intensity)*duration;
        }
    }

    private static float calculateCalories(float f1, float f2, float f3, float f4, float f5, Intensity intensity) {
        switch(intensity) {
            case VeryLight:
                return f1;
            case Light:
                return f2;
            case Moderate:
                return f3;
            case Vigorous:
                return f4;
            default:
                return f5;
        }
    }
}