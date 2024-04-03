package fr.univrouen.cv24.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class Fichier {

    private Resource resource;

    public Fichier() {
        this.resource = new DefaultResourceLoader().getResource("xml/smallCV.xml");
    }

    public String loadFileXML() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            return "Erreur lors du chargement du fichier : " + e.getMessage();
        }
    }
}