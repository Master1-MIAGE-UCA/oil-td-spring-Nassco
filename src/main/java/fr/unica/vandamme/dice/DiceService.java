package fr.unica.vandamme.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {

    private final Dice dice;
    private final DiceRollLogRepository diceRollLogRepository;

    @Autowired
    public DiceService(Dice dice, DiceRollLogRepository diceRollLogRepository) {
        this.dice = dice;
        this.diceRollLogRepository = diceRollLogRepository;
    }

    /**
     * Lance un certain nombre de dés, retourne les résultats et les enregistre dans l'historique.
     *
     * @param count Nombre de dés à lancer.
     * @return Liste des résultats.
     */
    public List<Integer> rollAndLogDice(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Le nombre de dés doit être supérieur à 0.");
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(dice.roll(6));
        }

        DiceRollLog log = new DiceRollLog(count, results, LocalDateTime.now());
        diceRollLogRepository.save(log);

        return results;
    }
}
