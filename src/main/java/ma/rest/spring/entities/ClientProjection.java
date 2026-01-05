package ma.rest.spring.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection pour afficher uniquement le nom et l'email du client
 * Usage: /api/clients/1?projection=clientDetails
 */
@Projection(name = "clientDetails", types = Client.class)
public interface ClientProjection {
    String getNom();
    String getEmail();
}
