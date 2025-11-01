package org.example;

/**
 * Represents the contract for general real estate properties.
 * <p>
 * This interface defines essential operations such as applying discounts,
 * calculating total price, and evaluating space efficiency per room.
 * Implementing classes should provide concrete logic based on their property type.
 * </p>
 *
 * @version 1.0
 */
public interface PropertyInterface {

    /**
     * Applies a discount to the property's price based on the given percentage.
     *
     * @param percentage the discount percentage to apply
     */
    void makeDiscount(double percentage);

    /**
     * Calculates the total price of the property after any adjustments.
     *
     * @return the final total price
     */
    int getTotalPrice();

    /**
     * Computes the average square meters per room.
     *
     * @return the average space per room in square meters
     */
    double averageSqmPerRoom();

    /**
     * Returns a one-line description of the property.
     *
     * @return a string representation of the property
     */
    String toString();
}