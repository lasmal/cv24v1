package fr.univrouen.cv24.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
	
	@GetMapping("/")
    public ResponseEntity<String> home() {
        String projectName = "Le Projet Du Module XML";
        String versionNumber = "1.0";
        String teamMembers = "LAALA Asma, FELLAH Kaouthar Manar";

        String universityLogo = "<img src=\"http://eriac.univ-rouen.fr/wp-content/uploads/2016/05/logo-univ-rouen-normandie-couleur.png\" style=\"height: 100px; width: auto;\">";


		String htmlContent = "<!DOCTYPE html>" +
				"<html>" +
				"<head><title>Accueil</title>" +
				"<style>" +
				".button {" +
				"  background-color: #ff69b4;" +  // Rose
				"  border: none;" +
				"  color: white;" +
				"  padding: 7px 15px;" +
				"  text-align: center;" +
				"  text-decoration: none;" +
				"  display: inline-block;" +
				"  font-size: 16px;" +
				"  margin: 4px 2px;" +
				"  cursor: pointer;" +
				"  border-radius: 8px;" + // Ajout de coins arrondis
				"}" +
				".container {" +
				"  overflow: auto;" + // Ajout de clearfix pour éviter le collapse du conteneur
				"}" +
				".left {" +
				"  float: left;" + // Aligner à gauche
				"}" +
				".right {" +
				"  float: right;" + // Aligner à gauche
				"}" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class=\"container\">" +
				"<div class=\"left\">" +
				"<h1>Bienvenue sur " + projectName + "</h1>" +
				"<p>Version : " + versionNumber + "</p>" +
				"<p>Membres de l'équipe : " + teamMembers + "</p>" + "</div>" +
				"<div class=\"right\">" + universityLogo + "</div>" +
				"</div>" +
				"<a href=\"/help\"><button class=\"button\">Aller à l'aide</button></a>" +
				"</body>" +
				"</html>";


		return ResponseEntity.status(HttpStatus.OK).body(htmlContent);
    }

	@GetMapping("/help")
	public ResponseEntity<String> getHelp() {
		String htmlContent = "<!DOCTYPE html>" +
				"<html lang=\"fr\">" +
				"<head>" +
				"<meta charset=\"UTF-8\">" +
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
				"<title>Aide</title>" +
				"<style>" +
				"body {" +
				"    font-family: Arial, sans-serif;" +
				"    margin: 0;" +
				"    padding: 0;" +
				"}" +
				".container {" +
				"    max-width: 800px;" +
				"    margin: 20px auto;" +
				"    padding: 20px;" +
				"    border-radius: 8px;" +
				"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
				"}" +
				"h2 {" +
				"    color: #ff69b4;" +
				"}" +
				"p {" +
				"    margin-bottom: 10px;" +
				"}" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class=\"container\">" +
				"<h1>Aide</h1>" +
				"<h2>Liste des opérations gérées par le service REST :</h2>" +
				"<ul>" +
				"<li>" +
				"<strong>Page d'accueil :</strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/\"> / </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche la page d'accueil</li>" +
				"<li>Format : HTML </li>" +
				"</ul>" +
				"</br>"+
				"</li>" +
				"<li>" +
				"<strong>Aide :</strong>" +
				"<ul>" +
				"<li>URL: <a href=\" /help \"> /help </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche la page contenant les informations d’aide</li>" +
				"<li>Format : HTML </li>" +
				"</ul>" +
				"</br>"+
				"</li>" +
				"<li>" +
				"<strong> Liste des CV : Format XML</strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/cv24/resume/xml\"> /cv24/resume/xml </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche la liste des CV stockés avec les informations demander</li>" +
				"<li>Format : Flux XML  </li>" +
				"</ul>" +
				"</br>"+
				"</li>" +
				"<li>" +
				"<strong> Liste des CV : Format HTML </strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/cv24/resume\"> /cv24/resume </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche Tableau des CV stockés  avec les informations demande</li>" +
				"<li>Format : PAGE HTML </li>" +
				"</ul>" +
				"</li>" +
				"</br>"+
				"<li>" +
				"<strong> Un CV spécifique en forme XML </strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/cv24/xml?id=662b99b16461ca818da62f71\"> /cv24/xml?id=UnIdDelaListePrecedente </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche le CV dans la base de données en utilisant l'identifiant</li>" +
				"<li>Format : XML </li>" +
				"</ul>" +
				"</br>"
				+"<li>" +
				"<strong> Un CV spécifique en forme HTML</strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/cv24/html?id=662b99b16461ca818da62f71\"> /cv24/html?id=UnIdDelaListePrecedente </a></li>" +
				"<li>Méthode : GET</li>" +
				"<li>Affiche le CV dans la base de données en utilisant l'identifiant</li>" +
				"<li>Format : HTML </li>" +
				"</ul>" +
				"</br>"+
				"<li>" +
				"<strong> Insertion d'un CV - ne se fais qu'en postman -  </strong>" +
				"<ul>" +
				"<li>URL: /cv24/insert </li>" +
				"<li>Méthode : POST </li>" +
				"<li> Insertion d'un CV données en XML en format JSON </li>" +
				"<li>Format : Le Json trouvé en mémoire  </li>" +
				"</ul>" +
				"</br>"+
				"<li>" +
				"<strong> supression d'un CV   </strong>" +
				"<ul>" +
				"<li>URL: <a href=\"/cv24/delete/xxx\"> /cv24/delete/UnIdDelaListePrecedente </a></li>" +
				"<li>Méthode : GET </li>" +
				"<li>supression d'un CV de la base de données </li>" +
				"<li>Format : HTML dans les deux cas , succées ou echec  </li>" +
				"</ul>" +
				"</br>"+
				"</li>" +
				"</ul>" +
				"</div>" +
				"</body>" +
				"</html>";

		return ResponseEntity.status(HttpStatus.OK).body(htmlContent);
	}


	
}