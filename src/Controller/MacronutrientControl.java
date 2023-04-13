package src.Controller;

import src.Entity.Macronutrients;

public class MacronutrientControl {
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;

    private String activityLevel;

    public MacronutrientControl() {

    }

    public int calculateBmr(double weight, double height, int age, String sex) {
        double bmr = 0;

        if (sex.equals("Male")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (sex.equals("Female")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        return (int) Math.round(bmr);
    }

    public int calculateTdee(int bmr, String activityLevel) {
        int tdee = 0;
        if (activityLevel.equals("Sedentary")){
            tdee = (int) (bmr * 1.19633658);
        } else if (activityLevel.equals("Light Exercise(1-2/Week)")) {
            tdee = (int) (bmr * 1.3750718);
        } else if (activityLevel.equals("Moderate Exercise(3-5/Week)")) {
            tdee = (int) (bmr * 1.55025847);
        } else if (activityLevel.equals("Heavy Exercise(6-7/Week)")) {
            tdee = (int) (bmr * 1.72544515);
        } else if (activityLevel.equals("Athlete(2x/Day)")) {
            tdee = (int) (bmr * 1.90005744);
        }
        return tdee;
    }

    public int adjustTdeeForGoal(int tdee, String goal) {
        if (goal.equals("Weight Gain")) {
            tdee += 500;
        } else if (goal.equals("Weight Loss")) {
            tdee -= 500;
        }
        else {
            tdee += 0;
        }
        return tdee;
    }

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
    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}

