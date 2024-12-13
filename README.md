[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/dnW0dm4q)  
# Projet "Dice" - Gestion de lancés de dés avec Spring Boot

## Description
Le projet "Dice" est une application construite avec Spring Boot permettant de simuler des lancés de dés et de gérer un historique des résultats en base de données. Les fonctionnalités liées aux lancés et aux logs sont maintenant séparées dans deux contrôleurs distincts :
- **`DiceController`** : Gère les actions liées aux dés.
- **`DiceLogController`** : Gère l'historique des lancés.

L'application intègre également une gestion centralisée des erreurs pour une expérience utilisateur améliorée.

---

## Fonctionnalités
- Lancer un dé à 6 faces.
- Lancer un nombre arbitraire de dés à 6 faces.
- Enregistrer les résultats des lancés dans une base de données (H2).
- Consulter l'historique complet des lancés via une API REST.
- Documentation interactive de l'API avec Swagger.
- Gestion centralisée des erreurs HTTP.

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
- **`GlobalExceptionHandler`** : Gestionnaire global pour centraliser la gestion des erreurs HTTP.

---

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

## Gestion des erreurs
L'application intègre un gestionnaire global des erreurs via la classe `GlobalExceptionHandler`. Ce gestionnaire permet d'intercepter les exceptions levées par l'application et de renvoyer des réponses JSON personnalisées.

### **Erreurs prises en charge :**

1. **404 Not Found** :
   - Gérée lorsqu'une URL demandée n'existe pas.
   - Exemple de réponse JSON :
     ```json
     {
         "timestamp": "2024-12-13T14:00:00",
         "status": 404,
         "error": "Not Found",
         "message": "L'URL demandée est introuvable : /api/invalid-endpoint"
     }
     ```

2. **500 Internal Server Error** :
   - Gérée pour toutes les exceptions non spécifiques ou génériques.
   - Exemple de réponse JSON :
     ```json
     {
         "timestamp": "2024-12-13T14:05:00",
         "status": 500,
         "error": "Internal Server Error",
         "message": "Une erreur inattendue s'est produite."
     }
     ```

### **Configuration associée**
Ajoutez cette propriété dans `application.properties` pour activer la gestion des erreurs 404 :
```properties
spring.web.resources.add-mappings=false
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
- Amélioration de la gestion des erreurs (messages plus détaillés).

---

## Auteur
Projet réalisé dans le cadre de [l'assignement GitHub Classroom](https://classroom.github.com/a/dnW0dm4q).  
