package src.Controller;

import src.Entity.Macronutrients;

/**
 * The MacronutrientControl class is responsible for calculating the daily caloric needs, macronutrient percentages, and
 * macronutrient amounts for a given user profile. It includes methods for calculating basal metabolic rate (BMR), total daily
 * energy expenditure (TDEE), adjusting TDEE for weight gain/loss goals, and calculating macronutrient amounts based on a
 * user's caloric needs and desired carbohydrate percentage. It also includes a method for setting the user's activity level.
 */
public class MacronutrientControl {
    private static final double LOW_CARBS = 0.2;
    private static final double MEDIUM_CARBS = 0.35;
    private static final double HIGH_CARBS = 0.5;

    private String activityLevel;

    /**
     * Constructs a MacronutrientControl object with no parameters.
     */
    public MacronutrientControl() {
    }

    /**
     * Calculates the basal metabolic rate (BMR) for a given user profile.
     *
     * @param weight the user's weight in kilograms
     * @param height the user's height in centimeters
     * @param age    the user's age in years
     * @param sex    the user's biological sex ("Male" or "Female")
     * @return the user's BMR as an integer
     */
    public int calculateBmr(double weight, double height, int age, String sex) {
        double bmr = 0;

        if (sex.equals("Male")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (sex.equals("Female")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        return (int) Math.round(bmr);
    }

    /**
     * Calculates the total daily energy expenditure (TDEE) for a given user profile and activity level.
     *
     * @param bmr           the user's BMR
     * @param activityLevel the user's activity level ("Sedentary", "Light Exercise(1-2/Week)", "Moderate Exercise(3-5/Week)",
     *                      "Heavy Exercise(6-7/Week)", or "Athlete(2x/Day)")
     * @return the user's TDEE as an integer
     */
    public int calculateTdee(int bmr, String activityLevel) {
        int tdee = 0;
        switch (activityLevel) {
            case "Sedentary":
                tdee = (int) (bmr * 1.19633658);
                break;
            case "Light Exercise(1-2/Week)":
                tdee = (int) (bmr * 1.3750718);
                break;
            case "Moderate Exercise(3-5/Week)":
                tdee = (int) (bmr * 1.55025847);
                break;
            case "Heavy Exercise(6-7/Week)":
                tdee = (int) (bmr * 1.72544515);
                break;
            case "Athlete(2x/Day)":
                tdee = (int) (bmr * 1.90005744);
                break;
        }
        return tdee;
    }

    /**
     * Adjust TDEE based on weight goal,
     * and calculate macronutrient requirements based on calorie intake and desired carbohydrate amount.
     *
     * @param tdee the total daily energy expenditure
     * @param goal the weight goal ("Weight Gain", "Weight Loss", or "Maintain Weight")
     * @return the adjusted TDEE as an integer
     */
    public int adjustTdeeForGoal(int tdee, String goal) {
        if (goal.equals("Weight Gain")) {
            tdee += 500;
        } else if (goal.equals("Weight Loss")) {
            tdee -= 500;
        }
        return tdee;
    }

    /**
     * Calculates macronutrient requirements based on the number of calories and preferred carb amount.
     *
     * @param calories   the total calorie intake
     * @param carbAmount the preferred carbohydrate amount ("Low", "Medium", or "High")
     * @return a Macronutrients object containing the calculated macronutrient amounts
     */
    public Macronutrients calculateMacronutrients(int calories, String carbAmount) {
        Macronutrients macronutrients = new Macronutrients();

        double proteinPercent = 0.3;
        double fatPercent = 0.35;
        double carbPercent = 0.35;

        if (carbAmount.equals("Low")) {
            carbPercent = LOW_CARBS;
        } else if (carbAmount.equals("Medium")) {
            carbPercent = MEDIUM_CARBS;
        } else if (carbAmount.equals("High")) {
            carbPercent = HIGH_CARBS;
            proteinPercent = 0.3;
            fatPercent = 0.2;
        }

        int protein = (int) Math.round(calories * proteinPercent / 4);
        int fat = (int) Math.round(calories * fatPercent / 9);
        int carbs = (int) Math.round(calories * carbPercent / 4);

        macronutrients.setProtein(protein);
        macronutrients.setFat(fat);
        macronutrients.setCarbs(carbs);

        return macronutrients;
    }

    /**
     * Sets the activity level.
     *
     * @param activityLevel the activity level to set
     */
    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
