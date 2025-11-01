package org.example;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Manages a collection of real estate properties and provides analysis tools.
 * <p>
 * This class supports loading property data from a file or sample dataset,
 * performing statistical analysis, and exporting results to a text file.
 * It handles both {@link RealEstate} and {@link Panel} objects.
 * </p>
 *
 * @version 1.0
 */
public class RealEstateAgent {

    /**
     * Logger instance for this class. Uses centralized logging configured in Main class.
     */
    private static final Logger logger = Logger.getLogger(RealEstateAgent.class.getName());

    /**
     * Stores all loaded real estate properties in a sorted set.
     */
    private static TreeSet<RealEstate> realEstates = new TreeSet<>();

    /**
     * Loads real estate data from a specified file.
     * <p>
     * The file must contain lines formatted as:
     * <ul>
     *   <li>{@code REALESTATE#city#price#sqm#rooms#genre}</li>
     *   <li>{@code PANEL#city#price#sqm#rooms#genre#floor#isInsulated}</li>
     * </ul>
     *
     * @param filename the name of the file to read from
     */
    public static void loadFromFile(String filename) {
        logger.info("Starting to load real estate data from file: " + filename);
        int loadedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            logger.info("File opened successfully: " + filename);
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    logger.info("Skipping empty line at line number: " + lineNumber);
                    continue;
                }

                try {
                    String[] parts = line.split("#");
                    String className = parts[0];
                    logger.info(String.format("Processing line %d: type=%s", lineNumber, className));

                    if (className.equalsIgnoreCase("REALESTATE")) {
                        String city = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        int sqm = Integer.parseInt(parts[3]);
                        int numberOfRooms = Integer.parseInt(parts[4]);
                        RealEstate.Genre genre = RealEstate.Genre.valueOf(parts[5].toUpperCase());

                        realEstates.add(new RealEstate(city, price, sqm, numberOfRooms, genre));
                        loadedCount++;
                        logger.info(String.format("Added RealEstate: city=%s, price=%.2f", city, price));

                    } else if (className.equalsIgnoreCase("PANEL")) {
                        String city = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        int sqm = Integer.parseInt(parts[3]);
                        int numberOfRooms = Integer.parseInt(parts[4]);
                        RealEstate.Genre genre = RealEstate.Genre.valueOf(parts[5].toUpperCase());
                        int floor = Integer.parseInt(parts[6]);
                        boolean isInsulated = parts[7].equalsIgnoreCase("yes");

                        realEstates.add(new Panel(city, price, sqm, numberOfRooms, genre, floor, isInsulated));
                        loadedCount++;
                        logger.info(String.format("Added Panel: city=%s, price=%.2f, floor=%d, insulated=%b",
                                city, price, floor, isInsulated));
                    } else {
                        logger.warning(String.format("Unknown property type '%s' at line %d", className, lineNumber));
                    }
                } catch (Exception e) {
                    logger.severe(String.format("Error parsing line %d: %s - Error: %s",
                            lineNumber, line, e.getMessage()));
                }
            }

            logger.info(String.format("File loading completed. Successfully loaded %d properties from file.", loadedCount));
            System.out.println("Successfully loaded " + realEstates.size() + " properties from file.");

        } catch (FileNotFoundException e) {
            logger.severe("File not found: " + filename + " - " + e.getMessage());
            System.err.println("File not found: " + filename);
            System.err.println("Please ensure the file exists or use loadSampleData() method.");
        } catch (IOException e) {
            logger.severe("Error reading file: " + filename + " - " + e.getMessage());
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Unexpected error while loading file: " + e.getMessage());
            System.err.println("Error parsing data: " + e.getMessage());
        }
    }

    /**
     * Loads a predefined set of sample real estate data.
     * <p>
     * This method is useful for testing or demonstration purposes.
     * </p>
     */
    public static void loadSampleData() {
        logger.info("Loading sample real estate data");
        try {
            realEstates.add(new RealEstate("Budapest", 250000, 100, 4, RealEstate.Genre.FLAT));
            realEstates.add(new RealEstate("Debrecen", 220000, 120, 5, RealEstate.Genre.FAMILYHOUSE));
            realEstates.add(new RealEstate("Nyíregyháza", 110000, 60, 2, RealEstate.Genre.FARM));
            realEstates.add(new RealEstate("Nyíregyháza", 250000, 160, 6, RealEstate.Genre.FAMILYHOUSE));
            realEstates.add(new RealEstate("Kisvárda", 150000, 50, 2, RealEstate.Genre.FLAT));
            realEstates.add(new Panel("Nyíregyháza", 150000, 68, 4, RealEstate.Genre.FLAT, 4, true));
            realEstates.add(new Panel("Budapest", 180000, 70, 3, RealEstate.Genre.FLAT, 4, false));
            realEstates.add(new Panel("Debrecen", 120000, 35, 2, RealEstate.Genre.FLAT, 0, true));
            realEstates.add(new Panel("Tiszaújváros", 120000, 750, 3, RealEstate.Genre.FLAT, 10, false));
            realEstates.add(new Panel("Nyíregyháza", 170000, 80, 3, RealEstate.Genre.FLAT, 7, false));

            logger.info("Sample data loaded successfully: " + realEstates.size() + " properties.");
            System.out.println("Sample data loaded: " + realEstates.size() + " properties.");
        } catch (Exception e) {
            logger.severe("Error loading sample data: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Displays statistical analysis of the loaded properties and writes results to a file.
     * <p>
     * The analysis includes:
     * <ul>
     *   <li>Average square meter price</li>
     *   <li>Cheapest property</li>
     *   <li>Most expensive property in Budapest</li>
     *   <li>Total price of all properties</li>
     *   <li>Affordable condominiums below average price</li>
     * </ul>
     * @throws IOException if writing to the output file fails
     */
    public static void displayResults() throws IOException {
        logger.info("Starting to display analysis results for " + realEstates.size() + " properties");

        if (realEstates.isEmpty()) {
            logger.warning("No properties loaded. Cannot display results.");
            System.out.println("No properties loaded. Cannot display results.");
            return;
        }

        try {
            StringBuilder output = new StringBuilder();
            output.append("===== REAL ESTATE AGENT ANALYSIS =====\n\n");

            // Analysis 1: Average square meter price
            logger.info("Calculating average square meter price");
            double avgSqmPrice = realEstates.stream()
                    .mapToDouble(RealEstate::getPrice)
                    .average()
                    .orElse(0.0);
            String result1 = String.format("1. Average square meter price: %.2f Ft\n", avgSqmPrice);
            output.append(result1);
            System.out.print(result1);
            logger.info(String.format("Average square meter price calculated: %.2f Ft", avgSqmPrice));

            // Analysis 2: Cheapest property
            logger.info("Finding cheapest property");
            RealEstate cheapest = realEstates.stream()
                    .min(Comparator.comparingInt(RealEstate::getTotalPrice))
                    .orElse(null);
            String result2 = String.format("2. Cheapest property total price: %d Ft\n",
                    cheapest != null ? cheapest.getTotalPrice() : 0);
            output.append(result2);
            System.out.print(result2);
            if (cheapest != null) {
                logger.info(String.format("Cheapest property found: %d Ft in %s",
                        cheapest.getTotalPrice(), cheapest.getCity()));
            } else {
                logger.warning("No cheapest property found");
            }

            // Analysis 3: Most expensive in Budapest
            logger.info("Finding most expensive property in Budapest");
            RealEstate mostExpensiveBudapest = realEstates.stream()
                    .filter(re -> re.getCity().equalsIgnoreCase("Budapest"))
                    .max(Comparator.comparingInt(RealEstate::getTotalPrice))
                    .orElse(null);
            String result3 = mostExpensiveBudapest != null
                    ? String.format("3. Most expensive Budapest property - avg sqm per room: %.2f m²\n",
                    mostExpensiveBudapest.averageSqmPerRoom())
                    : "3. No properties found in Budapest\n";
            output.append(result3);
            System.out.print(result3);
            if (mostExpensiveBudapest != null) {
                logger.info(String.format("Most expensive Budapest property: %.2f m² per room",
                        mostExpensiveBudapest.averageSqmPerRoom()));
            } else {
                logger.warning("No properties found in Budapest");
            }

            // Analysis 4: Total price
            logger.info("Calculating total price of all properties");
            long totalPrice = realEstates.stream()
                    .mapToLong(RealEstate::getTotalPrice)
                    .sum();
            String result4 = String.format("4. Total price of all properties: %d Ft\n", totalPrice);
            output.append(result4);
            System.out.print(result4);
            logger.info(String.format("Total price of all properties: %d Ft", totalPrice));

            // Analysis 5: Affordable condominiums
            logger.info("Finding affordable condominiums");
            double avgPrice = realEstates.stream()
                    .mapToInt(RealEstate::getTotalPrice)
                    .average()
                    .orElse(0.0);
            logger.info(String.format("Average property price: %.2f Ft", avgPrice));

            output.append("\n5. Condominium properties with price <= average price (")
                    .append(String.format("%.2f Ft):\n", avgPrice));
            System.out.print("\n5. Condominium properties with price <= average price ("
                    + String.format("%.2f Ft):\n", avgPrice));

            List<RealEstate> affordableCondos = realEstates.stream()
                    .filter(re -> re.getGenre() == RealEstate.Genre.FLAT)
                    .filter(re -> re.getTotalPrice() <= avgPrice)
                    .toList();

            if (affordableCondos.isEmpty()) {
                output.append("   No condominiums found within average price.\n");
                System.out.println("   No condominiums found within average price.");
                logger.info("No affordable condominiums found");
            } else {
                logger.info(String.format("Found %d affordable condominiums", affordableCondos.size()));
                for (RealEstate re : affordableCondos) {
                    String line = String.format("   - %s\n", re.toString());
                    output.append(line);
                    System.out.print(line);
                    logger.info(String.format("Affordable condo: %s, price: %d Ft",
                            re.getCity(), re.getTotalPrice()));
                }
            }

            // Write to file
            logger.info("Writing analysis results to outputRealEstate.txt");
            try (PrintWriter pw = new PrintWriter(new FileWriter("outputRealEstate.txt"))) {
                pw.print(output.toString());
                logger.info("Results successfully written to outputRealEstate.txt");
                System.out.println("\n===== Results written to outputRealEstate.txt =====");
            } catch (IOException e) {
                logger.severe("Failed to write results to file: " + e.getMessage());
                throw e;
            }

        } catch (Exception e) {
            logger.severe("Error during analysis and display: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the set of all loaded real estate properties.
     *
     * @return the set of properties
     */
    public static TreeSet<RealEstate> getRealEstates() {
        logger.info("Retrieving real estate collection, size: " + realEstates.size());
        return realEstates;
    }
}