[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/dnW0dm4q)  
# Projet "Dice" - Gestion de lancés de dés avec Spring Boot

## Description
Le projet "Dice" est une application construite avec Spring Boot permettant de simuler des lancés de dés et de gérer un historique des résultats en base de données. Les fonctionnalités liées aux lancés et aux logs sont maintenant séparées dans deux contrôleurs distincts :
- `DiceController` : Gère les actions liées aux dés.
- `DiceLogController` : Gère l'historique des lancés.

---

## Fonctionnalités
- Lancer un dé à 6 faces.
- Lancer un nombre arbitraire de dés à 6 faces.
- Enregistrer les résultats des lancés dans une base de données (H2).
- Consulter l'historique complet des lancés via une API REST.
- Documentation interactive de l'API avec Swagger.

---

## Prérequis
- **Java 17** ou version supérieure.
- **Maven** pour la gestion des dépendances.
- Un IDE compatible avec Spring Boot, comme IntelliJ IDEA ou Eclipse.

---

## Installation et exécution

1. **Cloner le projet** :
   ```bash
   git clone <URL-du-repo>
   cd dice
   ```

2. **Compiler et exécuter l'application** :
   ```bash
   mvn spring-boot:run
   ```

3. **Accéder à l'application** :
   - L'API est disponible à l'adresse : `http://localhost:8081/api`
   - La documentation Swagger est disponible à : `http://localhost:8081/swagger-ui.html`

---

## Endpoints de l'API

### Endpoints pour les lancés de dés
| Méthode | Endpoint              | Description                              |
|---------|-----------------------|------------------------------------------|
| `GET`   | `/api/rollDice`       | Lance un dé à 6 faces.                  |
| `GET`   | `/api/rollDices/{X}`  | Lance X dés à 6 faces.                  |

### Endpoints pour les logs
| Méthode | Endpoint       | Description                              |
|---------|----------------|------------------------------------------|
| `GET`   | `/api/logs`    | Récupère l'historique des lancés.        |

---

## Structure du projet
- **`DiceController`** : Contrôleur REST gérant les actions liées aux dés (lancer un ou plusieurs dés).
- **`DiceLogController`** : Contrôleur REST dédié à la gestion des logs (historique des lancés).
- **`Dice`** : Classe représentant un dé, permettant d'effectuer des lancés.
- **`DiceRollLog`** : Entité JPA modélisant un historique de lancer.
- **`DiceService`** : Service Spring contenant la logique métier.
- **`DiceRollLogRepository`** : Repository JPA pour gérer les interactions avec la base de données.

---

## Base de données
L'application utilise une base de données H2 en mémoire pour stocker les historiques. Vous pouvez accéder à la console H2 à l'adresse suivante :  
[http://localhost:8081/h2-console](http://localhost:8081/h2-console)  

### Configuration de la base
Les paramètres de connexion par défaut sont définis dans `application.properties` :
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

---

## Documentation Swagger
L'application intègre **Swagger** via SpringDoc OpenAPI pour documenter et tester les endpoints. Vous pouvez accéder à la documentation complète à l'adresse suivante :  
[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)  

### Contrôleurs documentés :
- **`DiceController`** : Documentation des endpoints pour lancer les dés.
- **`DiceLogController`** : Documentation des endpoints pour gérer les logs.

---

## Utilisation de Lombok
Le projet utilise **Lombok** pour réduire le code boilerplate. Installez le plugin Lombok dans votre IDE pour activer la génération automatique des getters, setters, constructeurs, etc.

---

## Améliorations possibles
- Ajout de tests unitaires et d'intégration.
- Ajout de fonctionnalités pour personnaliser les dés (nombre de faces).
- Mise en place d'une gestion avancée des erreurs (exception handler global).

---

## Auteur
Projet réalisé dans le cadre de [l'assignement GitHub Classroom](https://classroom.github.com/a/dnW0dm4q).  
