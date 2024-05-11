package fr.univrouen.cv24.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.univrouen.cv24.model.CV;

public interface CVRepository extends MongoRepository<CV, String> {
    // Ajoute des méthodes personnalisées si nécessaire
}
