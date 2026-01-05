# TP 11 : Spring Data REST

> **Cours** : Architecture Microservices : Conception, Déploiement et Orchestration

API REST automatique avec Spring Data REST pour la gestion de comptes bancaires.

## 🎬 Démo

https://github.com/user-attachments/assets/a8fb069b-f375-4290-a4dc-5c829ffe192d

## 📋 Fonctionnalités

- **CRUD automatique** sans contrôleurs manuels
- **Relations Client/Compte** avec liens HATEOAS
- **Projections** pour personnaliser les réponses
- **Recherches personnalisées** par type, solde, nom
- **Pagination et Tri** intégrés
- **Console H2** pour la gestion de la base

## 🛠️ Technologies

| Composant | Version |
|-----------|---------|
| Spring Boot | 3.2.0 |
| Spring Data REST | Auto |
| Spring Data JPA | Auto |
| H2 Database | Runtime |
| Lombok | Auto |

## 📁 Structure

```
src/main/java/ma/rest/spring/
├── MsBanqueApplication.java      # Application principale
├── entities/
│   ├── Compte.java               # Entité compte
│   ├── Client.java               # Entité client
│   ├── TypeCompte.java           # Enum COURANT/EPARGNE
│   ├── CompteProjection1.java    # Projection solde
│   ├── CompteProjection2.java    # Projection mobile
│   └── ClientProjection.java     # Projection client
└── repositories/
    ├── CompteRepository.java     # Repository REST comptes
    └── ClientRepository.java     # Repository REST clients
```

## 🚀 Démarrage

```bash
# Compiler et lancer
mvn spring-boot:run

# L'application démarre sur http://localhost:8082
```

## 📡 API Endpoints

### CRUD Comptes
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/comptes` | Liste tous les comptes |
| GET | `/api/comptes/{id}` | Obtenir un compte |
| POST | `/api/comptes` | Créer un compte |
| PUT | `/api/comptes/{id}` | Modifier un compte |
| DELETE | `/api/comptes/{id}` | Supprimer un compte |

### CRUD Clients
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/clients` | Liste tous les clients |
| GET | `/api/clients/{id}` | Obtenir un client |
| GET | `/api/clients/{id}/comptes` | Comptes d'un client |

### Recherches Personnalisées
```
GET /api/comptes/search/byType?t=EPARGNE
GET /api/comptes/search/byType?t=COURANT
GET /api/comptes/search/bySoldeGreaterThan?solde=1000
GET /api/comptes/search/byClientId?clientId=1
GET /api/clients/search/byNom?nom=Ali
GET /api/clients/search/byEmail?email=ali@example.com
```

### Projections
```
GET /api/comptes/1?projection=solde      → Affiche uniquement le solde
GET /api/comptes/1?projection=mobile     → Affiche solde + type
GET /api/clients/1?projection=clientDetails → Affiche nom + email
```

### Pagination et Tri
```
GET /api/comptes?page=0&size=2           → 2 comptes par page
GET /api/comptes?sort=solde,desc         → Tri par solde décroissant
GET /api/comptes?page=0&size=2&sort=solde,desc
```

## 💾 Console H2

- **URL**: http://localhost:8082/h2-console
- **JDBC URL**: `jdbc:h2:mem:banque`
- **User**: `sa`
- **Password**: *(vide)*

## 📊 Modèle de Données

```
┌─────────────┐       ┌─────────────┐
│   Client    │       │   Compte    │
├─────────────┤       ├─────────────┤
│ id          │       │ id          │
│ nom         │◄──────│ client_id   │
│ email       │  1:N  │ solde       │
│ comptes     │       │ dateCreation│
└─────────────┘       │ type        │
                      └─────────────┘
```

## ⚙️ Configuration

### application.properties
```properties
# Base de données H2
spring.datasource.url=jdbc:h2:mem:banque
spring.h2.console.enabled=true

# Port serveur
server.port=8082

# Chemin de base REST
spring.data.rest.base-path=/api
```

## 🧪 Tests avec cURL

### Découverte de l'API
```bash
curl -X GET 'http://localhost:8082/api'
```

### Liste des comptes
```bash
curl -X GET 'http://localhost:8082/api/comptes'
```

### Recherche par type
```bash
curl -X GET 'http://localhost:8082/api/comptes/search/byType?t=EPARGNE'
```

### Projection solde uniquement
```bash
curl -X GET 'http://localhost:8082/api/comptes?projection=solde'
```

### Projection mobile (solde + type)
```bash
curl -X GET 'http://localhost:8082/api/comptes?projection=mobile'
```

### Créer un compte
```bash
curl -X POST 'http://localhost:8082/api/comptes' \
  -H 'Content-Type: application/json' \
  -d '{"solde": 5000, "dateCreation": "2026-01-05", "type": "COURANT"}'
```

### Modifier un compte
```bash
curl -X PUT 'http://localhost:8082/api/comptes/1' \
  -H 'Content-Type: application/json' \
  -d '{"solde": 7500, "dateCreation": "2026-01-05", "type": "EPARGNE"}'
```

### Supprimer un compte
```bash
curl -X DELETE 'http://localhost:8082/api/comptes/1'
```

## 🔍 Exemple de Réponse JSON

```json
{
  "_embedded": {
    "comptes": [
      {
        "id": 1,
        "solde": 367.31034946619525,
        "dateCreation": "2024-11-06",
        "type": "EPARGNE",
        "_links": {
          "self": {"href": "http://localhost:8082/api/comptes/1"},
          "client": {"href": "http://localhost:8082/api/comptes/1/client"}
        }
      }
    ]
  },
  "_links": {
    "self": {"href": "http://localhost:8082/api/comptes"},
    "profile": {"href": "http://localhost:8082/api/profile/comptes"}
  },
  "page": {
    "size": 10,
    "totalElements": 5,
    "totalPages": 1,
    "number": 0
  }
}
```

## 👤 Auteur

**CHAKRAhossam**
