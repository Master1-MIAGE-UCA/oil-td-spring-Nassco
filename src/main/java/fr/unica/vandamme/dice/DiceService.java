package fr.unica.vandamme.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiceService {

    private final Dice dice;
    private final DiceRollLogRepository diceRollLogRepository;

    @Autowired
    public DiceService(Dice dice, DiceRollLogRepository diceRollLogRepository) {
        this.dice = dice;
        this.diceRollLogRepository = diceRollLogRepository;
    }

    public List<Integer> rollAndLogDice(int count, int faces) {
        if (count <= 0 || faces <= 0) {
            throw new IllegalArgumentException("Le nombre de dés et de faces doit être supérieur à 0.");
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(dice.roll(faces));
        }

        DiceRollLog log = new DiceRollLog(count, results, LocalDateTime.now());
        diceRollLogRepository.save(log);

        return results;
    }

    public DiceStats calculateStats() {
        // Récupère tous les résultats des logs
        List<Integer> allResults = diceRollLogRepository.findAll().stream()
                .flatMap(log -> log.getResults().stream())
                .collect(Collectors.toList());

        if (allResults.isEmpty()) {
            return new DiceStats(0, 0, 0, 0, 0); // Retourne des valeurs neutres si aucun résultat
        }

        // Calcul des statistiques
        IntSummaryStatistics stats = allResults.stream().mapToInt(Integer::intValue).summaryStatistics();
        double mean = stats.getAverage();
        int min = stats.getMin();
        int max = stats.getMax();
        double stdDev = calculateStandardDeviation(allResults, mean);
        double median = calculateMedian(allResults);

        return new DiceStats(mean, median, stdDev, min, max);
    }

    private double calculateStandardDeviation(List<Integer> values, double mean) {
        double variance = values.stream()
                .mapToDouble(v -> Math.pow(v - mean, 2))
                .average()
                .orElse(0);
        return Math.sqrt(variance);
    }

    private double calculateMedian(List<Integer> values) {
        List<Integer> sorted = values.stream().sorted().collect(Collectors.toList());
        int size = sorted.size();
        if (size % 2 == 0) {
            return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
        } else {
            return sorted.get(size / 2);
        }
    }
}
