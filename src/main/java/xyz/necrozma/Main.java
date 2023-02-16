package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Loaded Main");

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int allowedGuesses = 10;
        final int[] guesses = {1};

        logger.info("Correct Answer: " + randomNumber);



        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Press");
        JLabel info = new JLabel("");
        frame.add(info);
        frame.setLayout(new GridLayout());
        JTextField textField = new JTextField();
        frame.getContentPane().add(textField);
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String inputFieldValue = textField.getText();
                    if (guesses[0] == allowedGuesses) {
                        logger.debug("No More Guesses!");
                        info.setText("No More Guesses!");
                        return;
                    }


                    try {
                        int inputFieldValueAsNumber = Integer.parseInt(inputFieldValue);
                        if (inputFieldValueAsNumber == randomNumber) {
                            logger.debug("Correct!");
                            info.setText("Correct!");
                            frame.setVisible(true);
                        } else {
                            textField.setText("");
                            logger.debug("Guesses left:" + String.valueOf(allowedGuesses - arrayToInt(guesses)));
                            info.setText("Try again! You have: " + String.valueOf(allowedGuesses - arrayToInt(guesses)) + " guesses left...");
                            frame.setVisible(true);
                            guesses[0]++;
                        }

                    } catch (NumberFormatException err) {
                        logger.error("Not a number!");
                        info.setText("Not a number!");
                    }
                logger.debug("Button Clicked!");
            }
        });


    }

    static int arrayToInt(int[] arr)
    {
        //using a Stringbuilder is much more efficient than just using += on a String.
        //if this confuses you, just use a String and write += instead of append.
        StringBuilder s = new StringBuilder();

        for (int i : arr)
        {
            s.append(i); //add all the ints to a string
        }

        return Integer.parseInt(s.toString()); //parse integer out of the string
    }
}



