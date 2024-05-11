package fr.univrouen.cv24.model;

// LV.java
public class LV {
    private String lang;
    private String cert;
    private String nivs;
    private int nivi;

    public LV() {}

    public LV(String lang, String cert, String nivs, int nivi) {
        this.lang = lang;
        this.cert = cert;
        this.nivs = nivs;
        this.nivi = nivi;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getNivs() {
        return nivs;
    }

    public void setNivs(String nivs) {
        this.nivs = nivs;
    }

    public int getNivi() {
        return nivi;
    }

    public void setNivi(int nivi) {
        this.nivi = nivi;
    }
}

