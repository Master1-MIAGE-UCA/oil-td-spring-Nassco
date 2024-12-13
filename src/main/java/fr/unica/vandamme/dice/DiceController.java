package fr.unica.vandamme.dice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Dice Controller", description = "Endpoints pour gérer les lancés de dés et les logs")
public class DiceController {

    private static final Logger logger = LoggerFactory.getLogger(DiceController.class);
    private final DiceService diceService;
    private final DiceRollLogRepository diceRollLogRepository;

    @Autowired
    public DiceController(DiceService diceService, DiceRollLogRepository diceRollLogRepository) {
        this.diceService = diceService;
        this.diceRollLogRepository = diceRollLogRepository;
    }

    /**
     * Lancer un dé à 6 faces.
     *
     * @return Résultat du lancer.
     */
    @GetMapping("/rollDice")
    @Operation(summary = "Lancer un dé à 6 faces", description = "Lance un dé classique à 6 faces et retourne le résultat")
    public int rollSingleDice() {
        logger.info("Requête pour lancer un dé à 6 faces");
        int result = diceService.rollAndLogDice(1, 6).get(0);
        logger.info("Résultat du lancer : {}", result);
        return result;
    }

    /**
     * Lancer plusieurs dés à 6 faces.
     *
     * @param count Nombre de dés à lancer.
     * @return Liste des résultats.
     */
    @GetMapping("/rollDices/{count}")
    @Operation(summary = "Lancer plusieurs dés à 6 faces", description = "Lance plusieurs dés classiques à 6 faces")
    public List<Integer> rollMultipleDices(@PathVariable int count) {
        logger.info("Requête pour lancer {} dés à 6 faces", count);
        List<Integer> results = diceService.rollAndLogDice(count, 6);
        logger.info("Résultats des lancers : {}", results);
        return results;
    }

    /**
     * Lancer un dé avec un nombre de faces personnalisé.
     *
     * @param faces Nombre de faces du dé.
     * @return Résultat du lancer.
     */
    @GetMapping("/rollDice/{faces}")
    @Operation(summary = "Lancer un dé personnalisé", description = "Lance un dé avec un nombre de faces spécifié")
    public int rollSingleCustomDice(@PathVariable int faces) {
        logger.info("Requête pour lancer un dé avec {} faces", faces);
        int result = diceService.rollAndLogDice(1, faces).get(0);
        logger.info("Résultat du lancer : {}", result);
        return result;
    }

    /**
     * Lancer plusieurs dés avec un nombre de faces personnalisé.
     *
     * @param count Nombre de dés à lancer.
     * @param faces Nombre de faces de chaque dé.
     * @return Liste des résultats.
     */
    @GetMapping("/rollDices/{count}/{faces}")
    @Operation(summary = "Lancer plusieurs dés personnalisés", description = "Lance plusieurs dés avec un nombre de faces spécifié")
    public List<Integer> rollMultipleCustomDices(@PathVariable int count, @PathVariable int faces) {
        logger.info("Requête pour lancer {} dés avec {} faces", count, faces);
        List<Integer> results = diceService.rollAndLogDice(count, faces);
        logger.info("Résultats des lancers : {}", results);
        return results;
    }

    /**
     * Retourner l'historique des logs des lancés de dés.
     *
     * @return Liste des logs au format JSON.
     */
    @GetMapping("/diceLogs")
    @Operation(summary = "Récupérer l'historique des lancés", description = "Retourne tous les logs des lancés de dés")
    public List<DiceRollLog> getDiceLogs() {
        logger.info("Requête pour récupérer l'historique des logs des lancés");
        List<DiceRollLog> logs = diceRollLogRepository.findAll();
        logger.info("Nombre de logs récupérés : {}", logs.size());
        return logs;
    }

    @GetMapping("/logs/stats")
    @Operation(summary = "Statistiques des lancés", description = "Retourne les statistiques globales des lancés de dés (moyenne, médiane, écart-type, minimum, maximum)")
    public DiceStats getStats() {
        logger.info("Requête pour calculer les statistiques des lancés");
        DiceStats stats = diceService.calculateStats();
        logger.info("Statistiques calculées : {}", stats);
        return stats;
    }
}
