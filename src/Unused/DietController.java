package src.Unused;

/**
 * The DietController class manages the diets available in the system.
 *  It provides methods to retrieve three foods for each specific diet.
 */

public class DietController {

    private Diets diets;
    /**
     * Constructor for the DietController class.
     * Initializes a new Diets object.
     */
    public DietController() {
        this.diets = new Diets();
    }

    /**
     * Retrieves three vegetarian foods from the Diets object.
     * @return an array of three vegetarian foods
     */
    public String[] getThreeVegetarianFoods() {
        return diets.getThreeFoods(diets.getVegetarianFoods());
    }

    /**
     * Retrieves three gluten-free foods from the Diets object.
     * @return an array of three gluten-free foods
     */
    public String[] getThreeGlutenFreeFoods() {
        return diets.getThreeFoods(diets.getGlutenFreeFoods());
    }

    /**
     * Retrieves three vegan foods from the Diets object.
     * @return an array of three vegan foods
     */
    public String[] getThreeVeganFoods() {
        return diets.getThreeFoods(diets.getVeganFoods());
    }

    /**
     * Retrieves three dairy-free foods from the Diets object.
     * @return an array of three dairy-free foods
     */
    public String[] getThreedairyFreeFoods(){
        return  diets.getThreeFoods(diets.getDairyFreeFoods());
    }

    /**
     * Retrieves three carnivore foods from the Diets object.
     * @return an array of three carnivore foods
     */
    public String[] getThreecarnivoreFoods(){
        return  diets.getThreeFoods(diets.getCarnivoreFoods());
    }

    /**
     * Retrieves three keto foods from the Diets object.
     * @return an array of three keto foods
     */
    public String[] getThreeKetoFoods(){
        return  diets.getThreeFoods(diets.getKetoFoods());
    }

    /**
     * Retrieves three halal foods from the Diets object.
     * @return an array of three halal foods
     */
    public String[] getThreeHalalFoods(){
        return  diets.getThreeFoods(diets.getHalalFoods());
    }

    /**
     * Retrieves three pescatarian foods from the Diets object.
     * @return an array of three pescatarian foods
     */
    public String[] getThreePescatarianFoods(){
        return  diets.getThreeFoods(diets.getPescatarianFoods());
    }

    /**
     * Retrieves three paleo foods from the Diets object.
     * @return an array of three paleo foods
     */
    public String[] getThreePaleoFoods(){
        return  diets.getThreeFoods(diets.getPaleoFoods());
    }
    // add more methods for other diets as needed

}
