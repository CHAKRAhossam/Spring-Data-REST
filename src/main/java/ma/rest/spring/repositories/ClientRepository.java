package ma.rest.spring.repositories;

import ma.rest.spring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Repository pour l'entité Client
 * Spring Data REST expose automatiquement ce repository en tant que service RESTful
 */
@RepositoryRestResource(path = "clients", collectionResourceRel = "clients", itemResourceRel = "client")
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    /**
     * Recherche les clients par nom (contient)
     * URL: /api/clients/search/byNom?nom=Ali
     */
    @RestResource(path = "byNom")
    List<Client> findByNomContaining(@Param("nom") String nom);
    
    /**
     * Recherche un client par email
     * URL: /api/clients/search/byEmail?email=ali@example.com
     */
    @RestResource(path = "byEmail")
    Client findByEmail(@Param("email") String email);
}
