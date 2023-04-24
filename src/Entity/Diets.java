package src.Entity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

    public class Diets {

        private String[] vegetarianFoods = {"Tofu", "Bönor", "Cashewnötter", "Valnötter", "Frön", "Spannmål", "Tomat", "Linser", "Kikärtor", "Tempeh", "Spenat", "Broccoli", "Sötpotatis", "Äpplen"};
        private String[] glutenFreeFoods = {"Ris", "Potatis", "Quinoa", "Majs", "Bönor", "Nötter", "Frön", "Grönsaker", "Äpplen", "Kikärtor", "Hirs", "Bovete", "Durra", "Amarant"};
        private String[] veganFoods = {"Tofu", "Tempeh", "Seitan", "Bönor", "Linser", "Kikärtor", "Quinoa", "Bulgur", "Korn", "Fullkornsris", "Havre", "Äpplen", "Guacamole"};
        private String[] dairyFreeFoods = {"Mandelmjölk", "Kokosmjölk", "Sojamjölk", "Cashewost", "Näringsjäst", "Olivolja"};
        private String[] carnivoreFoods = {"Nötkött", "Fläskkött", "Kyckling", "Fisk", "Ägg", "Smör", "Ost"};
        private String[] ketoFoods = {"Kyckling", "Lax", "Ägg", "Cashewnötter", "Pumpakärnor", "Olivolja", "Kokosolja", "Avokado"};
        private String[] halalFoods = {"Kycklingbröst", "Nötfärs", "Fisk", "Blåbär", "Jordgubbar", "Aubergine", "Mozzarella"};
        private String[] pescatarianFoods = {"Lax", "Räkor", "Grönsaker", "Blåbär", "Havre", "Cashewnötter", "Frön"};
        private String[] paleoFoods = {"Nötfärs", "Kycklingbröst", "Lax", "Ägg", "Aubergine"};
        public String[] getThreeFoods(String[] foodsArray) {
            List<String> foodList = Arrays.asList(foodsArray);
            Collections.shuffle(foodList);
            return foodList.subList(0, 3).toArray(new String[0]);
        }


        public String[] getVegetarianFoods() {
            return vegetarianFoods;
        }

        public void setVegetarianFoods(String[] vegetarianFoods) {
            this.vegetarianFoods = vegetarianFoods;
        }

        public String[] getGlutenFreeFoods() {
            return glutenFreeFoods;
        }

        public void setGlutenFreeFoods(String[] glutenFreeFoods) {
            this.glutenFreeFoods = glutenFreeFoods;
        }

        public String[] getVeganFoods() {
            return veganFoods;
        }

        public void setVeganFoods(String[] veganFoods) {
            this.veganFoods = veganFoods;
        }

        public String[] getDairyFreeFoods() {
            return dairyFreeFoods;
        }

        public void setDairyFreeFoods(String[] dairyFreeFoods) {
            this.dairyFreeFoods = dairyFreeFoods;
        }

        public String[] getCarnivoreFoods() {
            return carnivoreFoods;
        }

        public void setCarnivoreFoods(String[] carnivoreFoods) {
            this.carnivoreFoods = carnivoreFoods;
        }

        public String[] getKetoFoods() {
            return ketoFoods;
        }

        public void setKetoFoods(String[] ketoFoods) {
            this.ketoFoods = ketoFoods;
        }

        public String[] getHalalFoods() {
            return halalFoods;
        }

        public void setHalalFoods(String[] halalFoods) {
            this.halalFoods = halalFoods;
        }

        public String[] getPescatarianFoods() {
            return pescatarianFoods;
        }

        public void setPescatarianFoods(String[] pescatarianFoods) {
            this.pescatarianFoods = pescatarianFoods;
        }

        public String[] getPaleoFoods() {
            return paleoFoods;
        }

        public void setPaleoFoods(String[] paleoFoods) {
            this.paleoFoods = paleoFoods;
        }
    }
