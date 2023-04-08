package src.Controller;

import src.Entity.Macronutrients;

public class MacronutrientControl {
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;

    private double activityLevel;

    public MacronutrientControl() {

    }

    public int calculateBmr(double weight, double height, int age, String sex) {
        double bmr = 0;

        if (sex.equals("male")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (sex.equals("female")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        return (int) Math.round(bmr);
    }

    public int calculateTdee(int bmr, double activityLevel) {
        double tdee = bmr * activityLevel;
        return (int) Math.round(tdee);
    }

    public int adjustTdeeForGoal(int tdee, String goal) {
        if (goal.equals("weightgain")) {
            tdee += 500;
        } else if (goal.equals("weightloss")) {
            tdee -= 500;
        }
        return tdee;
    }

    public Macronutrients calculateMacronutrients(int calories, String carbAmount) {
        Macronutrients macronutrients = new Macronutrients();

        double proteinPercent = 0.3;
        double fatPercent = 0.35;
        double carbPercent = 0.35;

        if (carbAmount.equals("low")) {
            carbPercent = LOW_CARBS;
        } else if (carbAmount.equals("medium")) {
            carbPercent = MEDIUM_CARBS;
        } else if (carbAmount.equals("high")) {
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
}

