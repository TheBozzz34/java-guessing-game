package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class GameLogic {

    private static final Logger logger = LoggerFactory.getLogger(GameLogic.class);
    private final int correctAnswer;
    private final int allowedGuesses;
    private int[] guesses;

    public GameLogic(int maxGuesses) {
        Random random = new Random();
        correctAnswer = random.nextInt(Configuration.maxAnswer) + 1;
        allowedGuesses = maxGuesses;
        guesses = new int[allowedGuesses];

        if (Configuration.debug) {
            logger.debug("Correct Answer: " + correctAnswer);
        }
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getAllowedGuesses() {
        return allowedGuesses;
    }

    public boolean guess(int guess) {
        guesses[getGuessCount()] = guess;
        return guess == correctAnswer;
    }

    public int getGuessCount() {
        int count = 0;
        for (int guess : guesses) {
            if (guess != 0) {
                count++;
            }
        }
        return count;
    }
}
