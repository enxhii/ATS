package ats.model;

import java.util.List;

public class CVInformation {

    private String emri;
    private String adresa;
    private String numri_telefonit;
    private String email;
    private String pozicioni;
    private List<ExperienceModel> eksperienca;

    public CVInformation () {

    }

    public CVInformation(String emri, String adresa, String numri_telefonit, String email, String pozicioni) {
        this.emri = emri;
        this.adresa = adresa;
        this.numri_telefonit = numri_telefonit;
        this.email = email;
        this.pozicioni = pozicioni;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNumri_telefonit() {
        return numri_telefonit;
    }

    public void setNumri_telefonit(String numri_telefonit) {
        this.numri_telefonit = numri_telefonit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPozicioni() {
        return pozicioni;
    }

    public void setPozicioni(String pozicioni) {
        this.pozicioni = pozicioni;
    }

    public List<ExperienceModel> getEksperienca() {
        return eksperienca;
    }

    public void setEksperienca(List<ExperienceModel> eksperienca) {
        this.eksperienca = eksperienca;
    }    

}