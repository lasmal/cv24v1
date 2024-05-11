package fr.univrouen.cv24.model;

public class Certif {
    private String datedeb;
    private String datefin;
    private String titre;

    public Certif() {}

    public Certif(String datedeb, String datefin, String titre) {
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.titre = titre;
    }

    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
