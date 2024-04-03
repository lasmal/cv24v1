package fr.univrouen.cv24.controllers;

import fr.univrouen.cv24.util.Fichier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
	@GetMapping("/resume")
	public String getListCVinXML() {
	return "Envoi de la liste des CV";
	}
	
	@GetMapping("/cvid")
	public String getCVinXML(
	@RequestParam(value = "texte") String texte) {
	return ("Détail du CV n°" + texte);
	}
	
	@GetMapping("/test")
	public String getCVinXMLTest(
	    @RequestParam(value = "id") int id, 
	    @RequestParam(value = "titre", required = false) String titre) {
	    String titreTexte = titre != null ? "Titre = " + titre : "Aucun titre spécifié";
	    return "Test : <br> id = " + id + " <br> " + titreTexte;
	}
	
	private final Fichier fichier;

    @Autowired
    public GetController(Fichier fichier) {
        this.fichier = fichier;
    }

    @GetMapping("/testfic")
    public String afficherContenuFichier() {
        return fichier.loadFileXML();
    }
}