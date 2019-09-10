package ats.model;


public class ExperienceModel {
    private String kompania;
    private String pozicioni;
    private String pershkrimi;

    public ExperienceModel() {

    }

    public ExperienceModel(String kompania, String pozicioni, String pershkrimi) {
        this.kompania = kompania;
        this.pozicioni = pozicioni;
        this.pershkrimi = pershkrimi;
    }

    public String getKompania() {
        return kompania;
    }

    public void setKompania(String kompania) {
        this.kompania = kompania;
    }

    public String getPozicioni() {
        return pozicioni;
    }

    public void setPozicioni(String pozicioni) {
        this.pozicioni = pozicioni;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }


}