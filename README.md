# Pool API

API REST de gestion de piscine développée avec Spring Boot 3.4.3.

## Technologies

- Java 21
- Spring Boot 3.4.3
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- JavaFaker (données de test)
- Springdoc OpenAPI (Swagger)

## Prérequis

- Java 21
- PostgreSQL
- Maven

## Installation

1. Cloner le dépôt
2. Créer une base de données PostgreSQL `pool_db`
3. Configurer `application.properties` :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pool_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
jwt.secret=YOUR_SECRET
jwt.expiration=86400000
```
4. Lancer l'application — les tables et données de test sont créées automatiquement

## Documentation API

Swagger disponible sur `http://localhost:8080/swagger-ui/index.html`

## Authentification

Toutes les routes (sauf `/auth/login`) nécessitent un token JWT.
```
POST /auth/login
{
    "email": "jean.dupont@mail.com",
    "password": "motdepasse123"
}
```

## Rôles

- **ADMIN** — accès complet
- **EMPLOYEE** — gestion des users
- **USER** — accès à ses propres données