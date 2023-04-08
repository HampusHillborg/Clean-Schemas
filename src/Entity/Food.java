package src.Entity;

public class Food {
    private int livsmedelsId;
    private String livsmedelsNamn;
    private double energiKcal;
    private double kolhydrater;
    private double protein;
    private double fett;

        public Food(int livsmedelsId, String livsmedelsNamn, double energiKcal, double kolhydrater, double protein, double fett) {
            this.livsmedelsId = livsmedelsId;
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

        public double getEnergiKcal() {
            return energiKcal;
        }

        public double getKolhydrater() {
            return kolhydrater;
        }

        public double getProtein() {
            return protein;
        }

        public double getFett() {
            return fett;
        }
    }
