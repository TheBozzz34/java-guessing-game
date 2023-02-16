package xyz.necrozma;

import java.util.Random;

public class GameLogic {
    private int correctAnswer;
    private int allowedGuesses;
    private int[] guesses;

    public GameLogic(int maxGuesses) {
        Random random = new Random();
        correctAnswer = random.nextInt(100) + 1;
        allowedGuesses = maxGuesses;
        guesses = new int[allowedGuesses];
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
