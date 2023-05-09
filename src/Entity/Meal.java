package src.Entity;

public class Meal {
    private int id;
    private String name;
    private double fat;
    private double carbs;
    private double protein;
    private double kcal;
    private String category;
    private String recommendedGrams;


    /**
     * Constructs a new Meal object with the given parameters.
     * @param id Unique identifier for the meal.
     * @param name Name of the meal
     * @param fat Amount of fat in the meal.
     * @param carbs  Amount of carbohydrates in the meal.
     * @param protein Amount of protein in the meal.
     * @param kcal Amount of kilocalories in the meal.
     * @param category Category of the meal, e.g. breakfast, lunch, dinner, snack.
     */
    public Meal(int id, String name, double fat, double carbs, double protein, double kcal, String category) {
        this.name = name;
        this.kcal = kcal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    /**
     * Returns the ID of the meal.
     * @return  the ID of the meal
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the meal.
     * @param id the new ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the amount of fat in the meal.
     * @param fat the amount of fat to be set
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Returns the category of the meal.
     * @return the category of the meal
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the category of the meal.
     * @param category the category of the meal
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Resturns the RecommendedGrams of the meal
     * @return the Recommended grams of the meal
     */
    public String getRecommendedGrams() {
        return recommendedGrams;
    }

    /**
     * Sets the recommended grams of the meal.
     * @param recommendedGrams the recommended grams of the meal
     */
    public void setRecommendedGrams(String recommendedGrams) {
        this.recommendedGrams = recommendedGrams;
    }

    /**
     * Returns the name of the meal.
     * @return the name of the meal
     */
    public String getName() {
        return name;
    }

    /**

     * Returns the amount of kilocalories in the meal.
     * @return the amount of kilocalories in the meal
     */
    public double getKcal() {
        return kcal;
    }

    /**

     * Returns the amount of fat in grams in the meal.
     * @return the amount of fat in grams in the meal
     */
    public double getFat() {
        return fat;
    }


    /**

     * Returns the amount of carbohydrates in grams in the meal.
     * @return the amount of carbohydrates in grams in the meal
     */
    public double getCarbs() {
        return carbs;
    }

    /**

     * Returns the amount of protein in this meal.
     * @return a double representing the amount of protein in grams
     */

    public double getProtein() {
        return protein;
    }

    /**

     * Sets the name of this meal.
     * @param name a String representing the new name of the meal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     * Sets the calorie count of this meal.
     * @param kcal a double representing the new calorie count of the meal
     */
    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    /**

     * Sets the amount of fat in this meal.
     * @param fat an int representing the new amount of fat in grams
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**

     * Sets the amount of carbs in this meal.
     * @param carbs the amount of carbs to set
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    /**

     * Sets the amount of protein in this meal.
     * @param protein the amount of protein to set
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }
}


