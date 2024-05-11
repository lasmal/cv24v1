// Competences.java
package fr.univrouen.cv24.model;

import java.util.ArrayList;
import java.util.List;

public class Competences {
    private List<Diplome> diplomes;
    private List<Certif> certifs;

    public Competences() {
        diplomes = new ArrayList<>();
        certifs = new ArrayList<>();
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }

    public void addDiplome(Diplome diplome) {
        diplomes.add(diplome);
    }

    public List<Certif> getCertifs() {
        return certifs;
    }

    public void addCertif(Certif certif) {
        certifs.add(certif);
    }
}
