package org.example;

/**
 * Defines the contract for panel-type real estate properties.
 * <p>
 * This interface provides methods for comparing property prices and calculating the price per room.
 * Implementing classes should provide logic specific to their property type.
 * </p>
 *
 * @version 1.0
 */
public interface PanelInterface {

    /**
     * Compares the total price of this property with another.
     *
     * @param otherRealEstate the property to compare with
     * @return true if both properties have the same total price; false otherwise
     */
    boolean hasSameAmount(RealEstate otherRealEstate);

    /**
     * Calculates the price of a single room in the property.
     *
     * @return the price per room
     */
    double roomPrice();
}