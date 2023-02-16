package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        logger.info("Loading game components");
        try {
            GameLogic GameLogic = new GameLogic(10);
            GameGui GameGui = new GameGui(GameLogic);
        } catch (Exception e) {
            logger.warn("Game loading failed!\n" + e.getMessage());
        }

    }
}



