package org.example;

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
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    /**
     * Returns the base price of the property.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the base price of the property.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the city where the property is located.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the property is located.
     *
     * @param city the city name to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the area of the property in square meters.
     *
     * @return the area in sqm
     */
    public double getSqm() {
        return sqm;
    }

    /**
     * Sets the area of the property in square meters.
     *
     * @param sqm the area to set
     */
    public void setSqm(double sqm) {
        this.sqm = sqm;
    }

    /**
     * Returns the number of rooms in the property.
     *
     * @return the number of rooms
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the number of rooms in the property.
     *
     * @param numberOfRooms the number of rooms to set
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Returns the genre or type of the property.
     *
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the genre or type of the property.
     *
     * @param genre the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Applies a discount to the property's price based on the given percentage.
     *
     * @param percentage the discount percentage to apply
     */
    @Override
    public void makeDiscount(double percentage) {
        this.price = price - (price * (percentage / 100));
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
        switch (city) {
            case "Budapest":
                price *= 1.30;
                break;
            case "Debrecen":
                price *= 1.20;
                break;
            case "Nyiregyhaza":
                price *= 1.15;
                break;
        }
        return (int) Math.round(price);
    }

    /**
     * Calculates the average square meters per room.
     *
     * @return the average space per room, or 0 if there are no rooms
     */
    @Override
    public double averageSqmPerRoom() {
        return (numberOfRooms != 0) ? sqm / numberOfRooms : 0;
    }

    /**
     * Returns a string representation of the property.
     *
     * @return a one-line description of the property
     */
    @Override
    public String toString() {
        return "RealEstate{" +
                "city='" + city + '\'' +
                ", price=" + price +
                ", sqm=" + sqm +
                ", numberOfRooms=" + numberOfRooms +
                ", genre=" + genre +
                '}';
    }
}