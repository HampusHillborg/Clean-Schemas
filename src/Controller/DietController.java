package src.Controller;

import src.Entity.Diets;

public class DietController {

    private Diets diets;
    public DietController() {
        this.diets = new Diets();
    }

    public String[] getThreeVegetarianFoods() {
        return diets.getThreeFoods(diets.getVegetarianFoods());
    }

    public String[] getThreeGlutenFreeFoods() {
        return diets.getThreeFoods(diets.getGlutenFreeFoods());
    }

    public String[] getThreeVeganFoods() {
        return diets.getThreeFoods(diets.getVeganFoods());
    }

    public String[] getThreedairyFreeFoods(){
        return  diets.getThreeFoods(diets.getDairyFreeFoods());
    }
    public String[] getThreecarnivoreFoods(){
        return  diets.getThreeFoods(diets.getCarnivoreFoods());
    }
    public String[] getThreeKetoFoods(){
        return  diets.getThreeFoods(diets.getKetoFoods());
    }
    public String[] getThreeHalalFoods(){
        return  diets.getThreeFoods(diets.getHalalFoods());
    }
    public String[] getThreePescatarianFoods(){
        return  diets.getThreeFoods(diets.getPescatarianFoods());
    }
    public String[] getThreePaleoFoods(){
        return  diets.getThreeFoods(diets.getPaleoFoods());
    }
    // add more methods for other diets as needed

}
