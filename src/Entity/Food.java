package src.Entity;

public class Food {
    private int livsmedelsId;
    private String livsmedelsNamn;
    private String energiKcal;
    private String kolhydrater;
    private String protein;
    private String fett;

    public Food(String livsmedelsNamn, String energiKcal, String kolhydrater, String protein,
            String fett) {
        this.livsmedelsNamn = livsmedelsNamn;
        this.energiKcal = energiKcal;
        this.kolhydrater = kolhydrater;
        this.protein = protein;
        this.fett = fett;
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
