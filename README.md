# Pool API

## 🇫🇷 Français

### Description
API REST de gestion d'accès à une piscine, développée avec Java Spring Boot. Ce projet permet de gérer les abonnés, les employés, les abonnements, les tickets et les accès à la piscine.

### Fonctionnalités
- Gestion des utilisateurs (abonnés) et des employés
- Gestion des abonnements et tickets d'entrée
- Contrôle des accès (entrée / sortie)
- Gestion de la capacité maximale de la piscine
- Sécurisation des endpoints par rôle (USER, EMPLOYEE, ADMIN)

### Stack technique
- Java 21
- Spring Boot 4
- Spring Security (JWT - à venir)
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Swagger / OpenAPI

### Lancer le projet
1. Cloner le repository
2. Copier `application.properties.example` en `application.properties` et renseigner vos identifiants PostgreSQL
3. Créer une base de données `pool_db` sur votre instance PostgreSQL
4. Lancer l'application avec `mvn spring-boot:run`
5. Accéder à la documentation Swagger : `http://localhost:8080/swagger-ui.html`

---

## 🇬🇧 English

### Description
REST API for managing pool access, built with Java Spring Boot. This project handles subscribers, employees, subscriptions, tickets, and pool access control.

### Features
- User (subscriber) and employee management
- Subscription and entry ticket management
- Access control (entry / exit)
- Pool maximum capacity management
- Role-based endpoint security (USER, EMPLOYEE, ADMIN)

### Tech stack
- Java 21
- Spring Boot 4
- Spring Security (JWT - coming soon)
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Swagger / OpenAPI

### Run the project
1. Clone the repository
2. Copy `application.properties.example` to `application.properties` and fill in your PostgreSQL credentials
3. Create a `pool_db` database on your PostgreSQL instance
4. Run the application with `mvn spring-boot:run`
5. Access Swagger documentation at `http://localhost:8080/swagger-ui.html`
