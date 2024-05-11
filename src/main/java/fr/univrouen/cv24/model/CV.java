package fr.univrouen.cv24.model;

import java.util.ArrayList;
import java.util.List;

public class CV {
    private String id;
    private String nom;
    private String prenom;
    private String email;

    private String objectif;
    private String genre;
    private String tel;
    private String statut; // added field for objectif statut

    private List<Prof> profs;
    private List<Diplome> diplomes;
    private List<Certif> certifs;
    private List<LV> lvList;
    private List<Autre> autreList;

    public CV() {
        profs = new ArrayList<>();
        diplomes = new ArrayList<>();
        certifs = new ArrayList<>();
        lvList = new ArrayList<>();
        autreList = new ArrayList<>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setObjectifStatut(String statut) {
        this.statut = statut;
    }

    public String getObjectifStatut() {
        return statut ;
    }
    public void addProf(Prof prof) {
        profs.add(prof);
    }

    public List<Prof> getProfs() {
        return profs;
    }

    public void addDiplome(Diplome diplome) {
        diplomes.add(diplome);
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }

    public void addCertif(Certif certif) {
        certifs.add(certif);
    }

    public List<Certif> getCertifs() {
        return certifs;
    }

    public void addLV(LV lv) {
        lvList.add(lv);
    }

    public List<LV> getLvList() {
        return lvList;
    }

    public void addAutre(Autre autre) {
        autreList.add(autre);
    }

    public List<Autre> getAutreList() {
        return autreList;
    }

}


/*
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cv")
public class CV {

    @Id
    private String id;

    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String objectif;
    private String diplome;
    private String genre;
    private String tel;
    private String statut;

    private String datedeb;


    // Constructeur, getters et setters

    public CV() {}

    public CV(String nom, String prenom, String dateNaissance, String email, String objectif, String diplome, String genre,String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.tel = tel;
        this.objectif = objectif;
        this.diplome = diplome;
        this.genre = genre;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setObjectifStatut(String statut) {
        this.statut = statut ;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public void addAutre(Autre autreObj) {
    }
}

*/