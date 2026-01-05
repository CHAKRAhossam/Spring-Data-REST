package ma.rest.spring;

import ma.rest.spring.entities.Client;
import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import ma.rest.spring.repositories.ClientRepository;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

/**
 * Application principale - TP11 Spring Data REST
 * 
 * Spring Data REST expose automatiquement les repositories en tant que services RESTful
 * sans nécessiter la création de contrôleurs manuels.
 */
@SpringBootApplication
public class MsBanqueApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MsBanqueApplication.class, args);
    }
    
    /**
     * Initialisation des données au démarrage de l'application
     */
    @Bean
    CommandLineRunner start(CompteRepository compteRepository, 
                           ClientRepository clientRepository,
                           RepositoryRestConfiguration restConfiguration) {
        return args -> {
            // Exposer les IDs dans les réponses JSON
            restConfiguration.exposeIdsFor(Compte.class, Client.class);
            
            System.out.println("=".repeat(60));
            System.out.println("Initialisation des données...");
            System.out.println("=".repeat(60));
            
            // Création des clients
            Client c1 = clientRepository.save(new Client(null, "Amal", "amal@example.com"));
            Client c2 = clientRepository.save(new Client(null, "Ali", "ali@example.com"));
            Client c3 = clientRepository.save(new Client(null, "Sara", "sara@example.com"));
            
            System.out.println("\n--- Clients créés ---");
            clientRepository.findAll().forEach(c -> 
                System.out.println("Client: " + c.getId() + " - " + c.getNom() + " - " + c.getEmail())
            );
            
            // Création des comptes avec association aux clients
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c1));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT, c1));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c2));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT, c2));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c3));
            
            System.out.println("\n--- Comptes créés ---");
            compteRepository.findAll().forEach(compte -> 
                System.out.println("Compte: " + compte.getId() + 
                                   " | Solde: " + String.format("%.2f", compte.getSolde()) + 
                                   " | Type: " + compte.getType() +
                                   " | Client: " + (compte.getClient() != null ? compte.getClient().getNom() : "N/A"))
            );
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Application démarrée sur http://localhost:8082");
            System.out.println("=".repeat(60));
            System.out.println("\n📌 Endpoints disponibles:");
            System.out.println("  - GET    /api/comptes                    → Liste tous les comptes");
            System.out.println("  - GET    /api/comptes/{id}               → Obtenir un compte");
            System.out.println("  - POST   /api/comptes                    → Créer un compte");
            System.out.println("  - PUT    /api/comptes/{id}               → Modifier un compte");
            System.out.println("  - DELETE /api/comptes/{id}               → Supprimer un compte");
            System.out.println("  - GET    /api/clients                    → Liste tous les clients");
            System.out.println("  - GET    /api/clients/{id}/comptes       → Comptes d'un client");
            System.out.println("\n📌 Recherches personnalisées:");
            System.out.println("  - GET    /api/comptes/search/byType?t=EPARGNE");
            System.out.println("  - GET    /api/comptes/search/bySoldeGreaterThan?solde=1000");
            System.out.println("  - GET    /api/clients/search/byNom?nom=Ali");
            System.out.println("\n📌 Projections:");
            System.out.println("  - GET    /api/comptes/1?projection=solde");
            System.out.println("  - GET    /api/comptes/1?projection=mobile");
            System.out.println("  - GET    /api/clients/1?projection=clientDetails");
            System.out.println("\n📌 Pagination et Tri:");
            System.out.println("  - GET    /api/comptes?page=0&size=2");
            System.out.println("  - GET    /api/comptes?sort=solde,desc");
            System.out.println("\n📌 Console H2: http://localhost:8082/h2-console");
            System.out.println("   JDBC URL: jdbc:h2:mem:banque");
            System.out.println("=".repeat(60));
        };
    }
}
