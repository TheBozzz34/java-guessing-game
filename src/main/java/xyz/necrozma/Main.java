package xyz.necrozma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger;

    public static void main(String[] args) {
        boolean debug = false;
        if (args.length > 0 && args[0].equals("-debug")) {
            Configuration.debug = true;
        }

        logger = LoggerFactory.getLogger(Main.class);

        if (Configuration.debug) {
            logger.debug("Debug logging enabled! Now loading game components...");
        }
        try {
            GameLogic gameLogic = new GameLogic(10);
            GameGui gameGui = new GameGui(gameLogic);
        } catch (Exception e) {
            logger.warn("Game loading failed!\n" + e.getMessage());
        }
    }
}
