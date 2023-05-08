package src.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an userprofile
 * and is used to store data of a user.
 */
public class Profile {
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private int age;
    private String sex;
    private double height;
    private double weight;
    private String goal;
    private String activityLevel;
    private String carbAmount;
    private String macroNutrient;
    private List<String> favoriteFoods;
    private List<String> dislikedFoods;
    private int bmr;
    private int tdee;
    private int mealsPerDay;
    private int fat;
    private int protein;

    private int carbs;
    private String dietCategory;

    /**
     * Constructs a Profile object
     * @param email
     * @param password
     */
    public Profile(String email, String password) {
        this.email = email;
        this.password = password;
        this.favoriteFoods = new ArrayList<String>();
        this.dislikedFoods = new ArrayList<String>();
    }

    /**
     * Adds more data to a profile
     * @param height
     * @param weight
     * @param age
     * @param sex
     * @param goal
     * @param activityLevel
     * @param carbIntake
     * @param mealsPerDay
     */
    public void addToProfile(double height, double weight, int age, String sex, String goal, String activityLevel,
            String carbIntake, int mealsPerDay) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.goal = goal;
        this.activityLevel = activityLevel;
        this.carbAmount = carbIntake;
        this.mealsPerDay = mealsPerDay;

    }

    public void setDietCategory(String category){
        this.dietCategory = category;
    }

    public String getDietCategory(){
        return dietCategory;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setActivityValue(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setCarbAmount(String carbAmount) {
        this.carbAmount = carbAmount;
    }

    public void setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public void setDislikedFoods(List<String> dislikedFoods) {
        this.dislikedFoods = dislikedFoods;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getGoal() {
        return goal;
    }

    public String getActivityValue() {
        return activityLevel;
    }

    public String getCarbAmount() {
        return carbAmount;
    }

    public List<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<String> getDislikedFoods() {
        return dislikedFoods;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public int getTdee() {
        return tdee;
    }

    public void setTdee(int tdee) {
        this.tdee = tdee;
    }

    public  int getFat(){return fat;}
    public void setFat(int fat){this.fat= fat;}

    public  int getCarbs(){return carbs;}
    public void setCarbs(int carbs){this.carbs= carbs;}

    public int getProtein() {
        return protein;
    }

    public String getCategory() {
        return dietCategory;
    }

    public void setProtein(int protein){this.protein= protein;}

    public int getMealsPerDay() {
        return mealsPerDay;
        /*
         * // Calculate the number of meals based on the user's weight and activity
         * level
         * if (weight < 68) {
         * return 3;
         * } else if (weight >= 68 && weight < 91) {
         * if (activityLevel.equals("Sedentary")) {
         * return 4;
         * } else {
         * return 5;
         * }
         * } else {
         * if (activityLevel.equals("Sedentary")) {
         * return 5;
         * } else {
         * return 6;
         * }
         * }
         * 
         */

    }
}
