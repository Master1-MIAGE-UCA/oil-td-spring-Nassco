package fr.unica.vandamme.dice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Dice Controller", description = "Endpoints pour gérer les lancés de dés")
public class DiceController {

    private final Dice dice;
    private final DiceRollLogRepository diceRollLogRepository;

    @Autowired
    public DiceController(Dice dice, DiceRollLogRepository diceRollLogRepository) {
        this.dice = dice;
        this.diceRollLogRepository = diceRollLogRepository;
    }

    /**
     * Lancer un seul dé.
     * @return Résultat du lancer.
     */
    @GetMapping("/rollDice")
    @Operation(summary = "Lancer un seul dé", description = "Lance un dé à 6 faces et retourne le résultat")
    public int rollSingleDice() {
        int result = dice.roll(6); // Par défaut, dé à 6 faces
        saveLog(1, List.of(result));
        return result;
    }

    /**
     * Lancer X dés.
     * @param count Nombre de dés à lancer.
     * @return Liste des résultats.
     */
    @GetMapping("/rollDices/{count}")
    @Operation(summary = "Lancer plusieurs dés", description = "Lance un nombre spécifié de dés à 6 faces et retourne les résultats")
    public List<Integer> rollMultipleDices(@PathVariable int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Le nombre de dés doit être supérieur à 0.");
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(dice.roll(6)); // Par défaut, dés à 6 faces
        }
        saveLog(count, results);
        return results;
    }

    /**
     * Affiche l'historique des lancers de dés.
     * @return Liste des enregistrements au format JSON.
     */
    @GetMapping("/diceLogs")
    @Operation(summary = "Afficher l'historique des lancés", description = "Retourne tous les logs des lancés de dés")
    public List<DiceRollLog> getAllLogs() {
        return diceRollLogRepository.findAll();
    }

    /**
     * Sauvegarde le log du lancer dans la base de données.
     * @param diceCount Nombre de dés lancés.
     * @param results Résultats des lancers.
     */
    private void saveLog(int diceCount, List<Integer> results) {
        DiceRollLog log = new DiceRollLog(diceCount, results, LocalDateTime.now());
        diceRollLogRepository.save(log);
    }
}
