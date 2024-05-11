package fr.univrouen.cv24.controllers;


import fr.univrouen.cv24.model.*;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univrouen.cv24.repository.CVRepository;
import fr.univrouen.cv24.util.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

import java.util.Optional;

@RestController
public class CVController {

    private final CVRepository cvRepository;
    private final CVService cvservice;

    @Autowired
    public CVController(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
		this.cvservice = new CVService();
    }
    
	@GetMapping("/testCVC")
	public String index() {
		return "Hello cv24 !";
		
	}

    @GetMapping("/cv24/resume/xml")
    public ResponseEntity<?> getCVsAsXML() {
        List  <CV> cvs = cvRepository.findAll();
        if (cvs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rien trouvé");
        } else {
            try {
                // Create a StringBuilder to construct the XML
                StringBuilder xmlBuilder = new StringBuilder();
                xmlBuilder.append("<CVs>");
                for (CV cv : cvs) {
                    xmlBuilder.append("<CV>");
                    xmlBuilder.append("<id>").append(cv.getId()).append("</id>");
                    xmlBuilder.append("<nom>").append(cv.getNom()).append("</nom>");
                    xmlBuilder.append("<prenom>").append(cv.getPrenom()).append("</prenom>");
                    xmlBuilder.append("<objectif>").append(cv.getObjectif()).append("</objectif>");
                    xmlBuilder.append("<genre>").append(cv.getGenre()).append("</genre>");

                    if (!cv.getDiplomes().isEmpty()) {
                        Diplome diplome = cv.getDiplomes().get(0);
                        xmlBuilder.append("<diplome>");
                        xmlBuilder.append("<date>").append(diplome.getDate()).append("</date>");
                        xmlBuilder.append("<institut>").append(diplome.getInstitut()).append("</institut>");
                        xmlBuilder.append("<niveau>").append(diplome.getNiveau()).append("</niveau>");
                        xmlBuilder.append("</diplome>");
                    }
                    xmlBuilder.append("</CV>");
                }
                xmlBuilder.append("</CVs>");

                // Créer l'en-tête de la réponse avec le type de contenu XML
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_XML);

                // Retourner la réponse avec le contenu XML et les en-têtes appropriés
                return new ResponseEntity<>(xmlBuilder.toString(), headers, HttpStatus.OK);

            } catch (Exception e) {
                // En cas d'erreur lors de la construction du XML
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du XML");
            }
        }
    }

    @GetMapping("/cv24/xml")
    public ResponseEntity<?> getCVByIdAsXML(@RequestParam("id") String id) {
        try {
            // Convertir l'identifiant en un objet ObjectId (ou tout autre type selon votre modèle)
            ObjectId objectId = new ObjectId(id);

            // Rechercher le CV dans la base de données en utilisant l'identifiant
            Optional<CV> cvOptional = cvRepository.findById(String.valueOf(objectId));

            // Vérifier si le CV existe
            if (cvOptional.isPresent()) {
                // Le CV existe, mapper en XML et retourner la réponse
                CV cv = cvOptional.get();
                XmlMapper xmlMapper = new XmlMapper();
                String xml = xmlMapper.writeValueAsString(cv);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_XML);

                return new ResponseEntity<>(xml, headers, HttpStatus.OK);
            } else {
                // Le CV n'existe pas, retourner une erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("<error><id>" + id + "</id><status>ERROR</status></error>");
            }
        } catch (Exception e) {
            // En cas d'erreur lors de la conversion en XML
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<error><id>" + id + "</id><status>ERROR</status></error>");
        }
    }


    @GetMapping("/cv24/resume")
    public String getResume(Model model) {
        List <CV> cvs = cvRepository.findAll();
        if (cvs.isEmpty()) {
            return "<h1>Liste des CV</h1><p>Rien trouvé</p>";
        } else {
            StringBuilder html = new StringBuilder();
            html.append("<style>table {margin: 20px; border-collapse: collapse;}  th, td {border: 1px solid black; padding: 8px;} th, h1 {color: #ff69b4;}</style>");

            html.append("<h1>Liste des CV</h1>");
            html.append("<table>");
            html.append("<thead><tr><th>ID</th><th>Nom</th><th>Prénom</th><th>Objectif</th><th>genre</th><th>Diplôme niveau</th></tr></thead>");
            html.append("<tbody>");
            for (CV cv : cvs) {
                html.append("<tr>");

                html.append("<td>").append(cv.getId()).append("</td>");
                html.append("<td>").append(cv.getNom()).append("</td>");
                html.append("<td>").append(cv.getPrenom()).append("</td>");
                html.append("<td>").append(cv.getObjectifStatut()).append(" ").append(cv.getObjectif()).append("</td>");
                html.append("<td>").append(cv.getGenre()).append("</td>");
                html.append("<td>").append(cv.getDiplomes().get(0).getNiveau()).append("</td>");
                html.append("</tr>");
            }
            html.append("</tbody>");
            html.append("</table>");
            return html.toString();
        }
    }

    @GetMapping("/cv24/html")
    public ResponseEntity<?> getCVByIdAsHTML(@RequestParam("id") String id) {
        // Vérifier si l'identifiant est un nombre valide
        try {
            // Convertir l'identifiant en un objet ObjectId (ou tout autre type selon votre modèle)
            ObjectId objectId = new ObjectId(id);

            // Rechercher le CV dans la base de données en utilisant l'identifiant
            Optional <CV> cvOptional = cvRepository.findById(String.valueOf(objectId));

            // Vérifier si le CV existe
            if (cvOptional.isPresent()) {
                // Le CV existe, créer le contenu HTML
                CV cv = cvOptional.get();
                String htmlContent = "<html>" +
                        "<style> h1, h3 {color: #ff69b4;} </style>" +
                        "<body>" +
                        "<h1>CV Details</h1>" +
                        "<p> ID: " + id + "</p>" +
                        "<h3>" + cv.getGenre() + " " +cv.getNom() + " " + cv.getPrenom() + "</h3>" +
                        "<p>Tel: " + cv.getTel() + "</p>" +
                        "<p>Email: " + cv.getEmail() + "</p>" +
                        "<h3>" + cv.getObjectif() + "</h3>" +
                        "<p>"+ cv.getObjectifStatut() + "</p>";

                // Displaying Professions
                List<Prof> profs = cv.getProfs();
                if (profs != null && !profs.isEmpty()) {
                    htmlContent += "<h3>Professions</h3><ul>";
                    for (Prof prof : profs) {
                        htmlContent += "<li>" + prof.getTitre() + " (du " + prof.getDatedeb() ;
                        if (prof.getDatefin()!= null) { htmlContent +=  " au " + prof.getDatefin(); }
                        htmlContent +=  " ) </li>";
                    }
                    htmlContent += "</ul>";
                }


                // Displaying Diplomes
                List<Diplome> diplomes = cv.getDiplomes();
                if (diplomes != null && !diplomes.isEmpty()) {
                    htmlContent += "<h3>Diplomes</h3><ul>";
                    for (Diplome diplome : diplomes) {
                        htmlContent += "<li>" + diplome.getDate() + "( Niveau "+ diplome.getNiveau() + ") " + diplome.getInstitut() + "</li>";
                    }
                    htmlContent += "</ul>";
                }


                // Displaying Certifs
                List<Certif> certifs = cv.getCertifs();
                if (certifs != null && !certifs.isEmpty()) {
                    htmlContent += "<h3>Certifications</h3><ul>";
                    for (Certif certif : certifs) {
                        htmlContent += "<li>" + certif .getTitre() + " (du " + certif .getDatedeb() ;
                        if (certif .getDatefin()!= null) { htmlContent +=  " au " + certif .getDatefin(); }
                        htmlContent +=  " ) </li>";}
                    htmlContent += "</ul>";
                }

                // Displaying LVs
                List<LV> lvList = cv.getLvList();
                if (lvList != null && !lvList.isEmpty()) {
                    htmlContent += "<h3>Language Proficiency</h3><ul>";
                    for (LV lv : lvList) {
                        htmlContent += "<li>" + lv .getLang() + " : " + lv .getCert() ;
                        if (lv .getNivs()!= null) { htmlContent +=  " (  " + lv.getNivs() + " ) "; }
                        if (lv .getNivi()!=0) { htmlContent +=  " (  " + lv.getNivi() + " ) "; }

                        htmlContent +=  " </li>";}
                    htmlContent += "</ul>";
                }

                // Displaying Autres
                List<Autre> autreList = cv.getAutreList();
                if (autreList != null && !autreList.isEmpty()) {
                    htmlContent += "<h3>Other Details</h3><ul>";
                    for (Autre autre : autreList) {
                        htmlContent += "<li>" + autre.getTitre() + ": " + autre.getComment() + "</li>";
                    }
                    htmlContent += "</ul>";
                }

                htmlContent += "</body></html>";

                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(htmlContent);
            } else {
                // Le CV n'existe pas, retourner une erreur
                String errorHtml = "<html><body>" +
                        "<h1>Error</h1>" +
                        "<p>ID: " + id + "</p>" +
                        "<p>Status: ERROR</p>" +
                        "</body></html>";

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.TEXT_HTML)
                        .body(errorHtml);
            }
        } catch (Exception e) {
            // En cas d'erreur
            String errorHtml = "<html><body>" +
                    "<h1>Error</h1>" +
                    "<p>ID: " + id + "</p>" +
                    "<p>Status: ERROR</p>" +
                    "</body></html>";

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_HTML)
                    .body(errorHtml);
        }
    }

    // Liste des CV au format JSON
	@GetMapping("/resume/json")
	public ResponseEntity<?> getListCV() {
	    List<CV> cvs = cvRepository.findAll();
	    if (cvs.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rien trouvé");
	    } else {
	        return ResponseEntity.ok(cvs);
	    }
	}


    @PostMapping("/cv24/insert")
    public ResponseEntity<CV> insertCV(@RequestBody String xmlData) {
        try {

            // Load XSD file
            InputStream xsdStream = getClass().getClassLoader().getResourceAsStream("templates/cv24.tp1.xsd");
            if (xsdStream == null) {
                return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body(null);
            }
            //System.out.println("hey");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            //System.out.println("hey1");
            Schema schema = factory.newSchema(new StreamSource(xsdStream));

            // System.out.println("hey2");
            // Validate XML against XSD
            Validator validator = schema.newValidator();

            //System.out.println("hey3");
            StreamSource xmlStream = new StreamSource(new StringReader(xmlData));

            //System.out.println("hey4");
            validator.validate(xmlStream);

            //System.out.println("hey5");
            // Convert XML to JSON
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(xmlData.getBytes());

            // Map JSON to CV object
            CV cv = new CV();

            JsonNode identiteNode = jsonNode.get("identite");
            if (identiteNode != null) {
                cv.setGenre(identiteNode.get("genre").asText());
                cv.setNom(identiteNode.get("nom").asText());
                cv.setPrenom(identiteNode.get("prenom").asText());
                cv.setEmail(identiteNode.get("mel").asText());
                cv.setTel(identiteNode.get("tel").asText());
            }

            JsonNode objectifNode = jsonNode.get("objectif");
            if (objectifNode != null) {
                // Extract statut attribute
                String statut = objectifNode.get("statut").asText();
                // Extract value
                String objectifValue = objectifNode.get("").asText().trim();

                // Set both statut and value in CV object
                cv.setObjectif(objectifValue);
                cv.setObjectifStatut(statut);
            }

            JsonNode profNode = jsonNode.get("prof");
            if (profNode != null) {
                // Extract details of prof
                JsonNode detailNode = profNode.get("detail");
                if (detailNode != null) {
                    // Check if it's an array of details or a single detail
                    if (detailNode.isArray()) {
                        // Iterate over each detail
                        for (JsonNode detail : detailNode) {
                            String datedeb = detail.get("datedeb").asText();
                            String datefin = detail.get("datefin") != null ? detail.get("datefin").asText() : null;
                            String titre = detail.get("titre").asText();

                            // Create Prof object and add to CV object
                            Prof prof = new Prof();
                            prof.setDatedeb(datedeb);
                            prof.setDatefin(datefin);
                            prof.setTitre(titre);
                            cv.addProf(prof);
                        }
                    } else {
                        // Single detail case
                        String datedeb = detailNode.get("datedeb").asText();
                        String datefin = detailNode.get("datefin") != null ? detailNode.get("datefin").asText() : null;
                        String titre = detailNode.get("titre").asText();

                        // Create Prof object and add to CV object
                        Prof prof = new Prof();
                        prof.setDatedeb(datedeb);
                        prof.setDatefin(datefin);
                        prof.setTitre(titre);
                        cv.addProf(prof);
                    }
                }
            }

            JsonNode competencesNode = jsonNode.get("competences");
            if (competencesNode != null) {
                // Extract diplomes
                JsonNode diplomeNode = competencesNode.get("diplome");
                if (diplomeNode != null)
                    // Check if it's an array
                    if (diplomeNode.isArray()) {
                        // Iterate over each
                        for (JsonNode diplome : diplomeNode) {
                            String date = diplome.get("date").asText();
                            String niveau = diplome.get("niveau").asText();
                            String institut = diplome.get("institut") != null ? diplome.get("institut").asText() : null;

                            // Create Diplome object and add to CV object
                            Diplome dip = new Diplome();
                            dip.setDate(date);
                            dip.setNiveau(niveau);
                            dip.setInstitut(institut);
                            cv.addDiplome(dip);
                        }
                    } else {
                        String date = diplomeNode.get("date").asText();
                        String niveau = diplomeNode.get("niveau").asText();
                        String institut = diplomeNode.get("institut") != null ? diplomeNode.get("institut").asText() : null;

                        // Create Diplome object and add to CV object
                        Diplome dip = new Diplome();
                        dip.setDate(date);
                        dip.setNiveau(niveau);
                        dip.setInstitut(institut);
                        cv.addDiplome(dip);

                        }
                }

                // Extract certifs
                JsonNode certifNode = competencesNode.get("certif");
                if (certifNode != null ) {
                    if (certifNode.isArray()) {
                        for (JsonNode certif : certifNode) {
                            String datedeb = certif.get("datedeb").asText();
                            String titre = certif.get("titre").asText();
                            // Create Certif object and add to CV object


                            Certif cer = new Certif();
                            cer.setDatedeb(datedeb);
                            cer.setTitre(titre);
                            if (certif.get("datefin") != null) { cer.setDatefin(certif.get("datefin").asText());}

                            cv.addCertif(cer);
                        }
                    } else {
                        String datedeb = certifNode.get("datedeb").asText();
                        String titre = certifNode.get("titre").asText();
                        // Create Certif object and add to CV object
                        Certif cer = new Certif();
                        cer.setDatedeb(datedeb);
                        if (certifNode.get("datefin") != null) { cer.setDatefin(certifNode.get("datefin").asText());}

                        cer.setTitre(titre);
                        cv.addCertif(cer);
                    }
                }

            JsonNode diversNode = jsonNode.get("divers");
            if (diversNode != null) {
                // Extract lv
                JsonNode lvNode = diversNode.get("lv");
                if (lvNode != null){
                    if (lvNode.isArray()) {
                        for (JsonNode lv : lvNode) {
                            String lang = lv.get("lang").asText();
                            String cert = lv.get("cert").asText();
                            String nivs = lv.has("nivs") ? lv.get("nivs").asText() : null;
                            int nivi = lv.has("nivi") ? lv.get("nivi").asInt() : 0;
                            // Create LV object and add to CV object
                            LV lvObj = new LV();
                            lvObj.setLang(lang);
                            lvObj.setCert(cert);
                            lvObj.setNivs(nivs);
                            lvObj.setNivi(nivi);
                            cv.addLV(lvObj);
                        }
                    } else {
                            String lang = lvNode.get("lang").asText();
                            String cert = lvNode.get("cert").asText();
                            String nivs = lvNode.has("nivs") ? lvNode.get("nivs").asText() : null;
                            int nivi = lvNode.has("nivi") ? lvNode.get("nivi").asInt() : 0;
                            // Create LV object and add to CV object
                            LV lvObj = new LV();
                            lvObj.setLang(lang);
                            lvObj.setCert(cert);
                            lvObj.setNivs(nivs);
                            lvObj.setNivi(nivi);
                            cv.addLV(lvObj);
                        }
                    }

                // Extract autres
                JsonNode autreNode = diversNode.get("autre");
                if (autreNode != null){
                    if (autreNode.isArray()) {
                        for (JsonNode autre : autreNode) {
                            String titre = autre.get("titre").asText();
                            String comment = autre.has("comment") ? autre.get("comment").asText() : null;
                            // Create Autre object and add to CV object
                            Autre autreObj = new Autre();
                            autreObj.setTitre(titre);
                            autreObj.setComment(comment);
                            cv.addAutre(autreObj);
                        }
                    } else  {
                            String titre = autreNode.get("titre").asText();
                            String comment = autreNode.has("comment") ? autreNode.get("comment").asText() : null;
                            // Create Autre object and add to CV object
                            Autre autreObj = new Autre();
                            autreObj.setTitre(titre);
                            autreObj.setComment(comment);
                            cv.addAutre(autreObj);
                        }
                    }
                }


            // Save CV object
            CV savedCV = cvRepository.save(cv);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCV);
        }catch (SAXException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }  catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    // Suppression d'un CV par ID
    @GetMapping ("/cv24/delete/{id}")
    public ResponseEntity<String> deleteCVById(@PathVariable String id) {
        if (cvRepository.existsById(id)) {
            cvRepository.deleteById(id);
            String successHtml = "<html><body>" +
                    "<h1>Suppression reussie</h1>" +
                    "<p>Le CV avec l'identifiant : " + id + "  a est supprime.</p>" +
                    "</body></html>";
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(successHtml);
        } else {
            String failHtml = "<html><body>" +
                    "<h1>Echec de la suppression</h1>" +
                    "<p>Le CV avec l'identifiant : " + id + " n'existe pas.</p>" +
                    "</body></html>";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_HTML).body(failHtml);
        }
    }
}
