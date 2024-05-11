package fr.univrouen.cv24.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.repository.CVRepository;

@Service
public class CVService {

    @Autowired
    private CVRepository cvRepository;

    public CV saveCV(CV cv) {
        return cvRepository.save(cv);
    }

    public CV getCVById(String id) {
        return cvRepository.findById(id).orElse(null);
    }

    // Ajoute d'autres méthodes selon tes besoins
}