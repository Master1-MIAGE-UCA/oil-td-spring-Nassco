package fr.unica.vandamme.dice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class DiceRollLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int diceCount;

    @ElementCollection
    @CollectionTable(name = "dice_roll_results", joinColumns = @JoinColumn(name = "log_id"))
    @Column(name = "result")
    private List<Integer> results;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Constructeurs

    public DiceRollLog() {
    }

    public DiceRollLog(int diceCount, List<Integer> results, LocalDateTime timestamp) {
        this.diceCount = diceCount;
        this.results = results;
        this.timestamp = timestamp;
    }

    // Getters et Setters

    @Override
    public String toString() {
        return "DiceRollLog{" +
                "id=" + id +
                ", diceCount=" + diceCount +
                ", results=" + results +
                ", timestamp=" + timestamp +
                '}';
    }
}