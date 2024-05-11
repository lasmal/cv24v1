package fr.univrouen.cv24.model;

public class Diplome {
    private String date;
    private String institut;
    private String niveau;

    public Diplome() {}

    public Diplome(String date, String institut, String niveau) {
        this.date = date;
        this.institut = institut;
        this.niveau = niveau;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
