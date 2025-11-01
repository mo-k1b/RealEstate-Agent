package org.example;

/**
 * Represents the contract for general real estate properties.
 * <p>
 * This interface defines essential operations such as applying discounts,
 * calculating total price, and evaluating space efficiency per room.
 * Implementing classes should provide concrete logic based on their property type.
 * </p>
 * <p>
 * <b>Logging Requirements:</b><br>
 * All implementing classes must use {@code java.util.logging.Logger} to log operations
 * to the file {@code realEstateApp.log}. The logging configuration is centralized in the
 * {@link Main} class. The following logging standards must be followed:
 * </p>
 * <ul>
 *   <li><b>INFO level</b>: Log all method calls with relevant parameter values and results</li>
 *   <li><b>SEVERE level</b>: Log all caught exceptions with descriptive error messages</li>
 *   <li>Logger should be initialized as: {@code Logger.getLogger(ClassName.class.getName())}</li>
 *   <li>Do NOT configure handlers in implementing classes - use centralized configuration</li>
 * </ul>
 *
 * @version 1.1
 * @author Mohammed Ba Dhib
 * @see java.util.logging.Logger
 * @see Main
 */
public interface PropertyInterface {

    /**
     * Applies a discount to the property's price based on the given percentage.
     * <p>
     * The discount is calculated as a percentage reduction from the current price.
     * For example, a 10% discount on a 100,000 Ft property results in a new price of 90,000 Ft.
     * </p>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level before applying discount: percentage value and current price</li>
     *   <li>Log at INFO level after applying discount: old price and new price</li>
     *   <li>Log at SEVERE level if any exception occurs during the operation</li>
     * </ul>
     * <p>
     * <b>Example log messages:</b>
     * </p>
     * <pre>
     * INFO: Applying discount of 10.00% to price 100000.00
     * INFO: Discount applied successfully. Price changed from 100000.00 to 90000.00
     * </pre>
     *
     * @param percentage the discount percentage to apply (e.g., 10.0 for 10%)
     * @throws IllegalArgumentException if percentage is negative or exceeds 100
     */
    void makeDiscount(double percentage);

    /**
     * Calculates the total price of the property after any adjustments.
     * <p>
     * Adjustments may include city-based multipliers, floor premiums, insulation bonuses,
     * or other factors depending on the implementing class. The calculation should be
     * deterministic and repeatable.
     * </p>
     * <p>
     * <b>City-based multipliers (for RealEstate class):</b>
     * </p>
     * <ul>
     *   <li>Budapest: base price × 1.30 (+30%)</li>
     *   <li>Debrecen: base price × 1.20 (+20%)</li>
     *   <li>Nyiregyhaza: base price × 1.15 (+15%)</li>
     *   <li>Other cities: no multiplier applied</li>
     * </ul>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level at method entry: base price and relevant parameters (e.g., city, floor)</li>
     *   <li>Log at INFO level for each adjustment applied (e.g., city multipliers, floor bonuses)</li>
     *   <li>Log at INFO level at method exit: final calculated total price</li>
     *   <li>Log at SEVERE level if calculation fails or exception occurs</li>
     * </ul>
     * <p>
     * <b>Example log messages:</b>
     * </p>
     * <pre>
     * INFO: Calculating total price for property in Budapest with base price 100000.00
     * INFO: Applied Budapest multiplier (1.30)
     * INFO: Total price calculated: 130000 (from base price 100000.00)
     * </pre>
     *
     * @return the final total price as an integer value (rounded if necessary)
     */
    int getTotalPrice();

    /**
     * Computes the average square meters per room.
     * <p>
     * This metric helps evaluate the spaciousness of each room in the property.
     * It is calculated by dividing the total square meters by the number of rooms.
     * If the property has zero rooms, implementations should return 0 to avoid
     * division by zero errors.
     * </p>
     * <p>
     * <b>Calculation formula:</b>
     * </p>
     * <pre>
     * averageSqmPerRoom = (numberOfRooms != 0) ? totalSqm / numberOfRooms : 0
     * </pre>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level at method entry: total sqm and number of rooms</li>
     *   <li>Log at INFO level if number of rooms is 0 (special case)</li>
     *   <li>Log at INFO level at method exit: calculated average value</li>
     *   <li>Log at SEVERE level if calculation error occurs</li>
     * </ul>
     * <p>
     * <b>Example log messages:</b>
     * </p>
     * <pre>
     * INFO: Calculating average sqm per room: sqm=100.00, rooms=4
     * INFO: Average sqm per room: 25.00
     * </pre>
     * <p>
     * OR for the special case:
     * </p>
     * <pre>
     * INFO: Calculating average sqm per room: sqm=100.00, rooms=0
     * INFO: No rooms available, returning 0
     * </pre>
     *
     * @return the average space per room in square meters, or 0 if no rooms exist
     */
    double averageSqmPerRoom();

    /**
     * Returns a one-line description of the property.
     * <p>
     * This method should provide a human-readable string representation of the property,
     * including all relevant attributes such as city, price, area, number of rooms, and genre.
     * The format should be consistent and parseable for debugging purposes.
     * </p>
     * <p>
     * <b>Expected format:</b>
     * </p>
     * <pre>
     * RealEstate{city='Budapest', price=100000.0, sqm=80.0, numberOfRooms=3, genre=FLAT}
     * </pre>
     * <p>
     * <b>Logging Requirements:</b>
     * </p>
     * <ul>
     *   <li>Log at INFO level when method is called</li>
     *   <li>Log at SEVERE level if string generation fails</li>
     * </ul>
     * <p>
     * <b>Example log message:</b>
     * </p>
     * <pre>
     * INFO: Generating string representation of RealEstate
     * </pre>
     *
     * @return a string representation of the property in a standard format
     */
    String toString();
}