[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/dnW0dm4q)  
# Projet "Dice" - Gestion de lancés de dés avec Spring Boot

## Description
Le projet "Dice" est une application construite avec Spring Boot permettant de simuler des lancés de dés et de gérer un historique des résultats en base de données. Les fonctionnalités incluent le lancer de dés, l'enregistrement des résultats, la consultation des logs et le calcul de statistiques sur les lancés. 

L'application est divisée en plusieurs modules : 
- **`DiceController`** : Gestion des actions liées aux dés et aux statistiques.
- **`DiceRollLog` et `DiceRollLogRepository`** : Gestion des logs des lancés de dés.
- **`DiceService`** : Logique métier pour le lancer, l'enregistrement des résultats et le calcul des statistiques.

---

## Fonctionnalités
- Lancer un dé à 6 faces.
- Lancer un nombre arbitraire de dés à 6 faces ou personnalisés.
- Enregistrer les résultats des lancés dans une base de données H2 en mémoire.
- Consulter l'historique des lancés via une API REST.
- Calculer des statistiques sur les lancés (moyenne, médiane, écart-type, minimum et maximum).
- Documentation Swagger interactive.

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
   - L'API est disponible à : `http://localhost:8081/api`
   - La documentation Swagger est disponible à : `http://localhost:8081/swagger-ui.html`

---

## Endpoints de l'API

### Endpoints pour les dés
| Méthode | Endpoint              | Description                              |
|---------|-----------------------|------------------------------------------|
| `GET`   | `/api/rollDice`       | Lance un dé à 6 faces.                  |
| `GET`   | `/api/rollDices/{X}`  | Lance X dés à 6 faces.                  |
| `GET`   | `/api/rollDice/{faces}` | Lance un dé avec un nombre de faces personnalisé. |
| `GET`   | `/api/rollDices/{count}/{faces}` | Lance plusieurs dés avec un nombre de faces personnalisé. |

### Endpoint pour les logs
| Méthode | Endpoint       | Description                              |
|---------|----------------|------------------------------------------|
| `GET`   | `/api/diceLogs`| Retourne l'historique des lancés.       |

### Endpoint pour les statistiques
| Méthode | Endpoint       | Description                              |
|---------|----------------|------------------------------------------|
| `GET`   | `/api/logs/stats` | Retourne les statistiques globales des lancés (moyenne, médiane, écart-type, minimum, maximum). |

---

## Structure du projet
- **`DiceController`** : Contrôleur REST exposant les fonctionnalités de lancer de dés, récupération des logs et calcul des statistiques.
- **`DiceService`** : Service pour la logique métier, comprenant le lancer de dés, l'enregistrement des résultats et le calcul des statistiques.
- **`DiceRollLog`** : Entité JPA représentant un log de lancer de dés.
- **`DiceRollLogRepository`** : Interface JPA pour interagir avec la base de données.
- **`Dice`** : Classe utilitaire pour gérer le lancer des dés.
- **`SwaggerConfig`** : Configuration de Swagger pour documenter l'API.

---

## Gestion des erreurs
L'application inclut un gestionnaire global pour les erreurs HTTP.

### **Erreurs gérées :**
1. **404 Not Found** : URL inexistante.
   - Exemple de réponse JSON :
     ```json
     {
         "timestamp": "2024-12-13T14:00:00",
         "status": 404,
         "error": "Not Found",
         "message": "L'URL demandée est introuvable : /api/invalid-endpoint"
     }
     ```

2. **500 Internal Server Error** : Problèmes internes.
   - Exemple de réponse JSON :
     ```json
     {
         "timestamp": "2024-12-13T14:05:00",
         "status": 500,
         "error": "Internal Server Error",
         "message": "Une erreur inattendue s'est produite."
     }
     ```

---

## Documentation Swagger
La documentation interactive Swagger est générée automatiquement via SpringDoc OpenAPI. Vous pouvez y accéder ici :  
[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

---

## Améliorations possibles
- Ajouter des tests unitaires pour valider le fonctionnement.
- Étendre la logique métier pour inclure des types de dés personnalisés (par exemple, dés spéciaux avec des faces non numériques).
- Ajouter des filtres dans les statistiques pour permettre des calculs par plage de dates ou par utilisateur.
- Ajouter une gestion des erreurs plus détaillée dans les réponses JSON.

---

## Auteur
Projet réalisé dans le cadre de [l'assignement GitHub Classroom](https://classroom.github.com/a/dnW0dm4q).
