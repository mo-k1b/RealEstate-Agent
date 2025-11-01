package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

/**
 * The {@code Main} class is the entry point for the Real Estate Agent application.
 * This class initializes the logging system and starts the main program flow.
 *
 * @author Mohammed Ba Dhib
 * @version v1.1
 * @since 10/2025
 * @see <a href="https://github.com/mo-k1b">Github profile</a>
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static FileHandler fileHandler;
    private static ConsoleHandler consoleHandler;

    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            fileHandler = new FileHandler("realEstateApp.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            rootLogger.addHandler(fileHandler);

            consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);
            rootLogger.addHandler(consoleHandler);

            rootLogger.setLevel(Level.ALL);

            logger.info("==========================================================");
            logger.info("=== Real Estate Application Started ===");
            logger.info("Application start time: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            logger.info("==========================================================");

        } catch (IOException e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main function to start the Real Estate Agent program.
     *
     * @param args command-line arguments (not currently used)
     */
    public static void main(String[] args) {
        logger.info("Main method execution started");

        try {
            logger.info("Attempting to load real estate data from file: realestates.txt");
            RealEstateAgent.loadFromFile("realestates.txt");
            logger.info("Data loading phase completed");

            logger.info("Attempting to display and analyze results");
            RealEstateAgent.displayResults();
            logger.info("Results display phase completed");

            logger.info("Program completed successfully without errors");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to output file: " + e.getMessage(), e);
            System.err.println("Error writing to output file: " + e.getMessage());
            System.err.println("Please check file permissions and disk space.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred in main method: " + e.getMessage(), e);
            System.err.println("Unexpected error: " + e.getMessage());
            System.err.println("Please check the log file for details.");
        } finally {
            logger.info("==========================================================");
            logger.info("=== Real Estate Application Ended ===");
            logger.info("Application end time: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            logger.info("==========================================================\n");

            if (fileHandler != null) {
                fileHandler.close();
            }
            if (consoleHandler != null) {
                consoleHandler.close();
            }
        }
    }
}