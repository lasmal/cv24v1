package fr.univrouen.cv24.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.cv24.model.TestCV;


@RestController

public class PostController {
	@RequestMapping(value = "/testpost", method = RequestMethod.POST, 
			consumes = "application/xml")
			public String postTest(@RequestBody String flux) {
			return ("<result><response>Message reçu : </response>" 
			+ flux + "</result>");
			}
	
	@RequestMapping(value="/testxml", produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody TestCV getXML() {
	TestCV cv = new TestCV("HAMILTON","Margaret","1969/07/21", 
	"Appollo11@nasa.us");
	return cv;
	}
}
