package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        RealEstateAgent.loadFromFile("realestates.txt");

        try {
            RealEstateAgent.displayResults();
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}