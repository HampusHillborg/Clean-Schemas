package src.Entity;

/**
 * A class that stores information for a food object
 */
public class Food {
    private int livsmedelsId;
    private String livsmedelsNamn;
    private String energiKcal;
    private String kolhydrater;
    private String protein;
    private String fett;

    /**
     * Constructs a Food object
     * @param livsmedelsNamn
     * @param energiKcal
     * @param kolhydrater
     * @param protein
     * @param fett
     */
    public Food(String livsmedelsNamn, String energiKcal, String kolhydrater, String protein,
            String fett) {
        this.livsmedelsNamn = livsmedelsNamn;
        this.energiKcal = energiKcal;
        this.kolhydrater = kolhydrater;
        this.protein = protein;
        this.fett = fett;
    }

    @Override
    public String toString() {
        return livsmedelsNamn + "\n"
                + "Energi (kcal): " + energiKcal + "\n"
                + "Kolhydrater (g): " + kolhydrater + "\n"
                + "Protein (g): " + protein + "\n"
                + "Fett (g): " + fett;
    }

    public int getLivsmedelsId() {
        return livsmedelsId;
    }

    public String getLivsmedelsNamn() {
        return livsmedelsNamn;
    }

    public String getEnergiKcal() {
        return energiKcal;
    }

    public String getKolhydrater() {
        return kolhydrater;
    }

    public String getProtein() {
        return protein;
    }

    public String getFett() {
        return fett;
    }
}
