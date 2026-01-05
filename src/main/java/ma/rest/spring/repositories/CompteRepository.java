package ma.rest.spring.repositories;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Repository pour l'entité Compte
 * Spring Data REST expose automatiquement ce repository en tant que service RESTful
 */
@RepositoryRestResource(path = "comptes", collectionResourceRel = "comptes", itemResourceRel = "compte")
public interface CompteRepository extends JpaRepository<Compte, Long> {
    
    /**
     * Recherche les comptes par type
     * URL: /api/comptes/search/byType?t=EPARGNE
     */
    @RestResource(path = "byType")
    List<Compte> findByType(@Param("t") TypeCompte type);
    
    /**
     * Recherche les comptes par solde supérieur à une valeur
     * URL: /api/comptes/search/bySoldeGreaterThan?solde=1000
     */
    @RestResource(path = "bySoldeGreaterThan")
    List<Compte> findBySoldeGreaterThan(@Param("solde") double solde);
    
    /**
     * Recherche les comptes d'un client
     * URL: /api/comptes/search/byClientId?clientId=1
     */
    @RestResource(path = "byClientId")
    List<Compte> findByClientId(@Param("clientId") Long clientId);
}
