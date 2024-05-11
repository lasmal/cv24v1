package fr.univrouen.cv24.model;

import java.util.ArrayList;
import java.util.List;

public class Divers {
    private List<LV> lvList;
    private List<Autre> autreList;

    public Divers() {
        lvList = new ArrayList<>();
        autreList = new ArrayList<>();
    }

    public List<LV> getLvList() {
        return lvList;
    }

    public void addLV(LV lv) {
        lvList.add(lv);
    }

    public List<Autre> getAutreList() {
        return autreList;
    }

    public void addAutre(Autre autre) {
        autreList.add(autre);
    }
}
