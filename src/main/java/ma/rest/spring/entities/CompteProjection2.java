package ma.rest.spring.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection pour afficher le solde et le type du compte (version mobile)
 * Usage: /api/comptes/1?projection=mobile
 */
@Projection(name = "mobile", types = Compte.class)
public interface CompteProjection2 {
    double getSolde();
    TypeCompte getType();
}
