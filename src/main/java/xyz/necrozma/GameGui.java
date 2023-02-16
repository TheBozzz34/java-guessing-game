package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui {
    private static Logger logger = LoggerFactory.getLogger(GameGui.class);

    private JFrame frame;
    private GameLogic game;
    private JTextField textField;
    private JLabel info;

    public GameGui(GameLogic game) {
        this.game = game;

        frame = new JFrame("Guess the Number Game");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        info = new JLabel("You have " + game.getAllowedGuesses() + " guesses remaining.");
        frame.add(info, BorderLayout.NORTH);

        textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        JButton button = new JButton("Guess");
        frame.add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFieldValue = textField.getText();

                try {
                    int guess = Integer.parseInt(inputFieldValue);
                    if (guess < 1 || guess > 100) {
                        info.setText("Please enter a number between 1 and 100.");
                        return;
                    }

                    if (game.guess(guess)) {
                        info.setText("Congratulations! You guessed the correct number: " + game.getCorrectAnswer());
                        button.setEnabled(false);
                    } else if (game.getGuessCount() == game.getAllowedGuesses()) {
                        info.setText("Sorry, you ran out of guesses. The correct number was " + game.getCorrectAnswer());
                        button.setEnabled(false);
                    } else {
                        info.setText("Sorry, that's not the correct number. You have " + (game.getAllowedGuesses() - game.getGuessCount()) + " guesses remaining.");
                        frame.pack();
                    }
                } catch (NumberFormatException err) {
                    info.setText("Please enter a valid number.");
                }

                logger.debug("Button Clicked!");
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
