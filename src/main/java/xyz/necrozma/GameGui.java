package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui {
    private static final Logger logger = LoggerFactory.getLogger(GameGui.class);

    private final JFrame frame;
    private GameLogic game;
    private final JTextField textField;
    private final JLabel info;

    public GameGui(GameLogic game) {
        this.game = game;

        frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        info = new JLabel("You have " + game.getAllowedGuesses() + " guesses remaining.");
        frame.add(info, BorderLayout.NORTH);

        textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        JButton button = new JButton("Guess");
        frame.add(button, BorderLayout.SOUTH);

        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                button.doClick();
            }
        };

        textField.addActionListener( action );

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFieldValue = textField.getText();

                try {
                    int guess = Integer.parseInt(inputFieldValue);
                    if (guess < 1 || guess > Configuration.maxAnswer) {
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
                        if (guess > game.getCorrectAnswer() ) {
                            info.setText("Sorry, that was too large. You have " + (game.getAllowedGuesses() - game.getGuessCount()) + " guesses remaining.");
                        } else {
                            info.setText("Sorry, that was too small. You have " + (game.getAllowedGuesses() - game.getGuessCount()) + " guesses remaining.");
                        }
                        //info.setText("Sorry, that's not the correct number. You have " + (game.getAllowedGuesses() - game.getGuessCount()) + " guesses remaining.");
                    }
                    frame.pack();
                } catch (NumberFormatException err) {
                    info.setText("Please enter a valid number.");
                }

                if (Configuration.debug) {
                    logger.debug("Button Clicked!");
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
