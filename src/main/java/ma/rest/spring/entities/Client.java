package ma.rest.spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entité représentant un client de la banque
 */
@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    
    private String email;
    
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Compte> comptes;
    
    public Client() {}
    
    public Client(Long id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
    
    public Client(Long id, String nom, String email, List<Compte> comptes) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.comptes = comptes;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<Compte> getComptes() { return comptes; }
    public void setComptes(List<Compte> comptes) { this.comptes = comptes; }
    
    @Override
    public String toString() {
        return "Client{id=" + id + ", nom='" + nom + "', email='" + email + "'}";
    }
}
