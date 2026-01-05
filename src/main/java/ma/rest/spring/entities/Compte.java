package ma.rest.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entité représentant un compte bancaire
 */
@Entity
public class Compte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double solde;
    
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    
    public Compte() {}
    
    public Compte(Long id, double solde, Date dateCreation, TypeCompte type) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }
    
    public Compte(Long id, double solde, Date dateCreation, TypeCompte type, Client client) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
        this.client = client;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    
    public TypeCompte getType() { return type; }
    public void setType(TypeCompte type) { this.type = type; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    @Override
    public String toString() {
        return "Compte{id=" + id + ", solde=" + solde + ", type=" + type + "}";
    }
}
