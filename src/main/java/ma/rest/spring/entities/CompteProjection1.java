package ma.rest.spring.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection pour afficher uniquement le solde du compte
 * Usage: /api/comptes/1?projection=solde
 */
@Projection(name = "solde", types = Compte.class)
public interface CompteProjection1 {
    double getSolde();
}
