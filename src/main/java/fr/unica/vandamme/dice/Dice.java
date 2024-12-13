package fr.unica.vandamme.dice;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Dice {
    private final Random random;

    public Dice() {
        this.random = new Random();
    }

    public int roll(int sides) {
        if (sides <= 0) {
            throw new IllegalArgumentException("Le nombre de faces doit être supérieur à 0.");
        }
        return random.nextInt(sides) + 1;
    }
}