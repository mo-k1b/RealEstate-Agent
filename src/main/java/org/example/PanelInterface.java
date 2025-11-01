package org.example;

/**
 * Defines the contract for panel-type real estate properties.
 * <p>
 * This interface provides methods for comparing property prices and calculating the price per room.
 * Implementing classes should provide logic specific to panel apartment buildings, which typically
 * have special characteristics such as floor levels and insulation status.
 * </p>
 * <p>
 * Panel properties are a specific type of real estate common in Hungary and other Eastern European
 * countries, typically built in the mid-to-late 20th century as prefabricated concrete apartment buildings.
 * </p>
 * <p>
 * <b>Logging Requirements:</b><br>
 * All implementing classes must use {@code java.util.logging.Logger} to log operations
 * to the file {@code realEstateApp.log}. The logging configuration is centralized in the
 * {@link Main} class.
 * </p>
 *
 * @version 1.1
 * @author Mohammed Ba Dhib
 * @see RealEstate
 * @see Panel
 */
public interface PanelInterface {

    /**
     * Compares the total price of this property with another real estate property.
     * <p>
     * This method performs an equality check on the total prices of two properties.
     * It is useful for finding properties in the same price range or for filtering
     * operations in collections.
     * </p>
     * <p>
     * <b>Implementation notes:</b>
     * </p>
     * <ul>
     *   <li>Should handle null input gracefully by returning false</li>
     *   <li>Should use {@link PropertyInterface#getTotalPrice()} for comparison</li>
     *   <li>Comparison should be based on integer equality (no floating-point issues)</li>
     * </ul>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level at method entry</li>
     *   <li>Log at INFO level if comparison target is null</li>
     *   <li>Log at INFO level with both prices being compared and the result</li>
     *   <li>Log at SEVERE level if exception occurs during comparison</li>
     * </ul>
     * <p>
     * <b>Example log messages:</b>
     * </p>
     * <pre>
     * INFO: Comparing total prices with another RealEstate
     * INFO: Price comparison: this=130000, other=130000, result=true
     * </pre>
     * <p>
     * OR if null:
     * </p>
     * <pre>
     * INFO: Comparing total prices with another RealEstate
     * INFO: Comparison target is null, returning false
     * </pre>
     *
     * @param otherRealEstate the property to compare with; may be null
     * @return true if both properties have the same total price; false otherwise or if input is null
     */
    boolean hasSameAmount(RealEstate otherRealEstate);

    /**
     * Calculates the price of a single room in the property.
     * <p>
     * This method computes the base price per room by dividing the property's base price
     * by the number of rooms. This is useful for evaluating the cost-effectiveness of
     * properties with different numbers of rooms.
     * </p>
     * <p>
     * <b>Calculation formula:</b>
     * </p>
     * <pre>
     * roomPrice = (numberOfRooms != 0) ? basePrice / numberOfRooms : 0
     * </pre>
     * <p>
     * <b>Important distinction:</b> This method uses the base price (before city multipliers
     * and other adjustments), not the total price. This provides a normalized metric for
     * comparing properties across different cities.
     * </p>
     * <p>
     * <b>Implementation notes:</b>
     * </p>
     * <ul>
     *   <li>Should return 0 if numberOfRooms is 0 to avoid division by zero</li>
     *   <li>Should use the base price, not the adjusted total price</li>
     *   <li>Result should be a double value for precision</li>
     * </ul>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level at method entry: base price and number of rooms</li>
     *   <li>Log at INFO level at method exit: calculated room price or 0 if no rooms</li>
     *   <li>Log at SEVERE level if calculation error occurs</li>
     * </ul>
     * <p>
     * <b>Example log messages:</b>
     * </p>
     * <pre>
     * INFO: Calculating room price: base price=120000.00, rooms=3
     * INFO: Room price calculated: 40000.00
     * </pre>
     * <p>
     * OR for the special case:
     * </p>
     * <pre>
     * INFO: Calculating room price: base price=120000.00, rooms=0
     * INFO: No rooms available, returning 0
     * </pre>
     *
     * @return the price per room in the property's base currency (Ft), or 0 if there are no rooms
     */
    double roomPrice();
}