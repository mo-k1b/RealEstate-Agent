package org.example;

import java.util.logging.*;

/**
 * Represents a general real estate property with attributes such as city, price, area, number of rooms, and genre.
 * <p>
 * This class implements the {@link PropertyInterface} and provides logic for calculating total price,
 * applying discounts, and evaluating space efficiency.
 * </p>
 *
 * @version 1.0
 * @author Mohammed Ba Dhib
 */
public class RealEstate implements PropertyInterface {

    /**
     * Logger instance for this class. Uses centralized logging configured in Main class.
     */
    private static final Logger logger = Logger.getLogger(RealEstate.class.getName());

    /**
     * The city where the property is located.
     */
    String city;

    /**
     * The base price of the property.
     */
    double price;

    /**
     * The area of the property in square meters.
     */
    double sqm;

    /**
     * The number of rooms in the property.
     */
    int numberOfRooms;

    /**
     * Represents the type of real estate property.
     */
    public enum Genre {
        /** A standalone family house. */
        FAMILYHOUSE,

        /** A condominium or apartment. */
        FLAT,

        /** A rural farm property. */
        FARM
    }

    /**
     * The genre of the property.
     */
    Genre genre;

    /**
     * Constructs a default RealEstate instance.
     */
    public RealEstate() {
        logger.info("Creating new RealEstate instance with default constructor");
    }

    /**
     * Constructs a RealEstate instance with specified details.
     *
     * @param city           the city where the property is located
     * @param price          the base price of the property
     * @param sqm            the area of the property in square meters
     * @param numberOfRooms  the number of rooms in the property
     * @param genre          the genre or type of the property
     */
    public RealEstate(String city, double price, double sqm, int numberOfRooms, Genre genre) {
        logger.info(String.format("Creating RealEstate: city=%s, price=%.2f, sqm=%.2f, rooms=%d, genre=%s",
                city, price, sqm, numberOfRooms, genre));

        try {
            this.city = city;
            this.price = price;
            this.sqm = sqm;
            this.numberOfRooms = numberOfRooms;
            this.genre = genre;
            logger.info("RealEstate instance created successfully");
        } catch (Exception e) {
            logger.severe("Error creating RealEstate instance: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the base price of the property.
     *
     * @return the price
     */
    public double getPrice() {
        logger.info("Getting price: " + price);
        return price;
    }

    /**
     * Sets the base price of the property.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        logger.info(String.format("Setting price from %.2f to %.2f", this.price, price));
        try {
            this.price = price;
        } catch (Exception e) {
            logger.severe("Error setting price: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the city where the property is located.
     *
     * @return the city name
     */
    public String getCity() {
        logger.info("Getting city: " + city);
        return city;
    }

    /**
     * Sets the city where the property is located.
     *
     * @param city the city name to set
     */
    public void setCity(String city) {
        logger.info(String.format("Setting city from '%s' to '%s'", this.city, city));
        try {
            this.city = city;
        } catch (Exception e) {
            logger.severe("Error setting city: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the area of the property in square meters.
     *
     * @return the area in sqm
     */
    public double getSqm() {
        logger.info("Getting sqm: " + sqm);
        return sqm;
    }

    /**
     * Sets the area of the property in square meters.
     *
     * @param sqm the area to set
     */
    public void setSqm(double sqm) {
        logger.info(String.format("Setting sqm from %.2f to %.2f", this.sqm, sqm));
        try {
            this.sqm = sqm;
        } catch (Exception e) {
            logger.severe("Error setting sqm: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the number of rooms in the property.
     *
     * @return the number of rooms
     */
    public int getNumberOfRooms() {
        logger.info("Getting number of rooms: " + numberOfRooms);
        return numberOfRooms;
    }

    /**
     * Sets the number of rooms in the property.
     *
     * @param numberOfRooms the number of rooms to set
     */
    public void setNumberOfRooms(int numberOfRooms) {
        logger.info(String.format("Setting number of rooms from %d to %d", this.numberOfRooms, numberOfRooms));
        try {
            this.numberOfRooms = numberOfRooms;
        } catch (Exception e) {
            logger.severe("Error setting number of rooms: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the genre or type of the property.
     *
     * @return the genre
     */
    public Genre getGenre() {
        logger.info("Getting genre: " + genre);
        return genre;
    }

    /**
     * Sets the genre or type of the property.
     *
     * @param genre the genre to set
     */
    public void setGenre(Genre genre) {
        logger.info(String.format("Setting genre from %s to %s", this.genre, genre));
        try {
            this.genre = genre;
        } catch (Exception e) {
            logger.severe("Error setting genre: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Applies a discount to the property's price based on the given percentage.
     *
     * @param percentage the discount percentage to apply
     */
    @Override
    public void makeDiscount(double percentage) {
        logger.info(String.format("Applying discount of %.2f%% to price %.2f", percentage, price));
        try {
            double oldPrice = this.price;
            this.price = price - (price * (percentage / 100));
            logger.info(String.format("Discount applied successfully. Price changed from %.2f to %.2f",
                    oldPrice, this.price));
        } catch (Exception e) {
            logger.severe("Error applying discount: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Calculates the total price of the property based on its city.
     * <p>
     * City-based multipliers:
     * <ul>
     *     <li>Budapest: +30%</li>
     *     <li>Debrecen: +20%</li>
     *     <li>Nyiregyhaza: +15%</li>
     * </ul>
     *
     * @return the final total price
     */
    @Override
    public int getTotalPrice() {
        logger.info(String.format("Calculating total price for property in %s with base price %.2f",
                city, price));
        try {
            double originalPrice = price;
            switch (city) {
                case "Budapest":
                    price *= 1.30;
                    logger.info("Applied Budapest multiplier (1.30)");
                    break;
                case "Debrecen":
                    price *= 1.20;
                    logger.info("Applied Debrecen multiplier (1.20)");
                    break;
                case "Nyiregyhaza":
                    price *= 1.15;
                    logger.info("Applied Nyiregyhaza multiplier (1.15)");
                    break;
                default:
                    logger.info("No city multiplier applied for: " + city);
            }
            int totalPrice = (int) Math.round(price);
            logger.info(String.format("Total price calculated: %d (from base price %.2f)",
                    totalPrice, originalPrice));
            return totalPrice;
        } catch (Exception e) {
            logger.severe("Error calculating total price: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Calculates the average square meters per room.
     *
     * @return the average space per room, or 0 if there are no rooms
     */
    @Override
    public double averageSqmPerRoom() {
        logger.info(String.format("Calculating average sqm per room: sqm=%.2f, rooms=%d",
                sqm, numberOfRooms));
        try {
            double average = (numberOfRooms != 0) ? sqm / numberOfRooms : 0;
            if (numberOfRooms == 0) {
                logger.info("No rooms available, returning 0");
            } else {
                logger.info(String.format("Average sqm per room: %.2f", average));
            }
            return average;
        } catch (Exception e) {
            logger.severe("Error calculating average sqm per room: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns a string representation of the property.
     *
     * @return a one-line description of the property
     */
    @Override
    public String toString() {
        logger.info("Generating string representation of RealEstate");
        try {
            String result = "RealEstate{" +
                    "city='" + city + '\'' +
                    ", price=" + price +
                    ", sqm=" + sqm +
                    ", numberOfRooms=" + numberOfRooms +
                    ", genre=" + genre +
                    '}';
            return result;
        } catch (Exception e) {
            logger.severe("Error generating toString: " + e.getMessage());
            throw e;
        }
    }
}