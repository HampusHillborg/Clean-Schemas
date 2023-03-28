package src.Entity;

import java.util.List;

public class Profile {
    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setCarbAmount(String carbAmount) {
        this.carbAmount = carbAmount;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public void setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public void setDislikedFoods(List<String> dislikedFoods) {
        this.dislikedFoods = dislikedFoods;
    }

    private double height;
    private double weight;
    private int age;
    private String sex;
    private String goal;
    private String carbAmount;
    private int mealsPerDay;
    private List<String> favoriteFoods;
    private List<String> dislikedFoods;

    public Profile(double height, double weight, int age, String sex, String goal, String carbAmount, int mealsPerDay) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.goal = goal;
        this.carbAmount = carbAmount;
        this.mealsPerDay = mealsPerDay;
/*        this.favoriteFoods = favoriteFoods;
        this.dislikedFoods = dislikedFoods;

 */
    }


    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getGoal() {
        return goal;
    }

    public String getCarbAmount() {
        return carbAmount;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public List<String> getFavouriteFoods() {
        return favoriteFoods;
    }

    public List<String> getDislikeFoods() {
        return dislikedFoods;
    }


}
