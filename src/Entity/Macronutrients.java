package src.Entity;

/**
 * Class that stores the Macronutrients
 */
public class Macronutrients {
    private int protein;
    private int fat;
    private int carbs;

    public Macronutrients() {
    }


    /**
     * Returns the amount of protein in grams.
     * @return the amount of protein in grams
     */
    public int getProtein() {
        return protein;
    }


    /**
     * Sets the amount of protein in grams.
     * @param protein protein the amount of protein in grams
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }


    /**
     * Returns the amount of fat in grams.
     * @return the amount of fat in grams
     */
    public int getFat() {
        return fat;
    }


    /**
     * Sets the amount of fat in grams.
     * @param fat fat the amount of fat in grams
     */
    public void setFat(int fat) {
        this.fat = fat;
    }


    /**
     * Returns the amount of carbohydrates in grams.
     * @return the amount of carbohydrates in grams
     */
    public int getCarbs() {
        return carbs;
    }


    /**
     * Sets the amount of carbohydrates in grams.
     * @param carbs carbs the amount of carbohydrates in grams
     */
    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }
}
