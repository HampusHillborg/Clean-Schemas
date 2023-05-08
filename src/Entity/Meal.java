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

    public Meal(int id, String name, double fat, double carbs, double protein, double kcal, String category) {
        this.name = name;
        this.kcal = kcal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRecommendedGrams() {
        return recommendedGrams;
    }

    public void setRecommendedGrams(String recommendedGrams) {
        this.recommendedGrams = recommendedGrams;
    }

    public String getName() {
        return name;
    }

    public double getKcal() {
        return kcal;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }
}


