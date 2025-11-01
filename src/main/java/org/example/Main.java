package org.example;
import java.io.IOException;


/**
 *
 * The {@code Main} class is the entry point for the application.
 * @author Mohammed Ba Dhib
 * @version v1.0
 * @since 10/2025
 * @see <a href = "https://github.com/mo-k1b">Github profile</a>
 */
public class Main {
    /**
     * main function to start the program
     * @param args recieves the command-line arguments
     */
    public static void main(String[] args) {
        RealEstateAgent.loadFromFile("realestates.txt");
        try {
            RealEstateAgent.displayResults();
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}