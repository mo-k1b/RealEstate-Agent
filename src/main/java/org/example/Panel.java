package org.example;

import java.util.logging.*;

/**
 * Represents a panel-type real estate property.
 * <p>
 * This class extends {@link RealEstate} by adding specific attributes such as floor number and insulation status,
 * and implements the {@link PanelInterface} to provide additional behavior.
 * </p>
 *
 * @version 1.0
 * @author Mohammed Ba Dhib
 */
public class Panel extends RealEstate implements PanelInterface {

    /**
     * Logger instance for this class. Uses centralized logging configured in Main class.
     */
    private static final Logger logger = Logger.getLogger(Panel.class.getName());

    /**
     * The number of floors in the property.
     */
    int floor;

    /**
     * Indicates whether the property is insulated.
     */
    boolean isInsulated;

    /**
     * Constructs a default Panel instance.
     */
    public Panel() {
        super();
        logger.info("Creating new Panel instance with default constructor");
    }

    /**
     * Constructs a Panel with the specified property details.
     *
     * @param city           the city where the property is located
     * @param price          the price of the property
     * @param sqm            the area of the property in square meters
     * @param numberOfRooms  the number of rooms in the property
     * @param genre          the type of the property
     * @param floor          the number of floors in the property
     * @param isInsulated    whether the property is insulated
     */
    public Panel(String city, double price, double sqm, int numberOfRooms, Genre genre, int floor,
                 boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        logger.info(String.format("Creating Panel: floor=%d, isInsulated=%b", floor, isInsulated));

        try {
            this.floor = floor;
            this.isInsulated = isInsulated;
            logger.info("Panel instance created successfully");
        } catch (Exception e) {
            logger.severe("Error creating Panel instance: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the number of floors in the property.
     *
     * @return the number of floors
     */
    public int getFloor() {
        logger.info("Getting floor: " + floor);
        return floor;
    }

    /**
     * Sets the number of floors in the property.
     *
     * @param floor the number of floors to set
     */
    public void setFloor(int floor) {
        logger.info(String.format("Setting floor from %d to %d", this.floor, floor));
        try {
            this.floor = floor;
        } catch (Exception e) {
            logger.severe("Error setting floor: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns whether the property is insulated.
     *
     * @return true if the property is insulated; false otherwise
     */
    public boolean isInsulated() {
        logger.info("Getting insulation status: " + isInsulated);
        return isInsulated;
    }

    /**
     * Sets the insulation status of the property.
     *
     * @param insulated true if the property is insulated; false otherwise
     */
    public void setInsulated(boolean insulated) {
        logger.info(String.format("Setting insulation status from %b to %b", this.isInsulated, insulated));
        try {
            isInsulated = insulated;
        } catch (Exception e) {
            logger.severe("Error setting insulation status: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Calculates the total price of the property, including adjustments for floor level and insulation.
     * Overrides {@link RealEstate#getTotalPrice()}.
     *
     * @return the final total price
     */
    @Override
    public int getTotalPrice() {
        logger.info(String.format("Calculating Panel total price: floor=%d, isInsulated=%b", floor, isInsulated));

        try {
            int price = super.getTotalPrice();
            logger.info(String.format("Base total price from RealEstate: %d", price));

            int originalPrice = price;

            if (floor >= 0 && floor <= 2) {
                price *= 1.05;
                logger.info(String.format("Applied low floor bonus (1.05) for floor %d: price changed from %d to %d",
                        floor, originalPrice, price));
                originalPrice = price;
            } else if (floor == 10) {
                price *= 0.95;
                logger.info(String.format("Applied top floor penalty (0.95) for floor 10: price changed from %d to %d",
                        originalPrice, price));
                originalPrice = price;
            } else {
                logger.info(String.format("No floor adjustment for floor %d", floor));
            }

            if (isInsulated) {
                price *= 1.05;
                logger.info(String.format("Applied insulation bonus (1.05): price changed from %d to %d",
                        originalPrice, price));
            } else {
                logger.info("No insulation bonus applied");
            }

            logger.info(String.format("Final Panel total price: %d", price));
            return price;

        } catch (Exception e) {
            logger.severe("Error calculating Panel total price: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns a one-line description of the property.
     * Overrides {@link PanelInterface}.
     *
     * @return a brief description of the property
     */
    @Override
    public String toString() {
        logger.info("Generating string representation of Panel");
        try {
            String result = super.toString() +
                    " is a Panel with " +
                    "floor=" + floor +
                    ", isInsulated=" + isInsulated;
            return result;
        } catch (Exception e) {
            logger.severe("Error generating Panel toString: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Compares the total price of this property with another.
     * Overrides {@link PanelInterface#hasSameAmount(RealEstate)}.
     *
     * @param realEstate the property to compare with
     * @return true if both properties have the same total price; false otherwise
     */
    @Override
    public boolean hasSameAmount(RealEstate realEstate) {
        logger.info("Comparing total prices with another RealEstate");

        try {
            if (realEstate == null) {
                logger.info("Comparison target is null, returning false");
                return false;
            }

            int thisPrice = this.getTotalPrice();
            int otherPrice = realEstate.getTotalPrice();
            boolean result = otherPrice == thisPrice;

            logger.info(String.format("Price comparison: this=%d, other=%d, result=%b",
                    thisPrice, otherPrice, result));
            return result;

        } catch (Exception e) {
            logger.severe("Error comparing prices: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Calculates the price per room.
     * Overrides {@link PanelInterface#roomPrice()}.
     *
     * @return the price of a single room, or 0 if there are no rooms
     */
    @Override
    public double roomPrice() {
        logger.info(String.format("Calculating room price: base price=%.2f, rooms=%d",
                super.price, numberOfRooms));

        try {
            if (numberOfRooms != 0) {
                double roomPrice = super.price / numberOfRooms;
                logger.info(String.format("Room price calculated: %.2f", roomPrice));
                return roomPrice;
            }

            logger.info("No rooms available, returning 0");
            return 0;

        } catch (Exception e) {
            logger.severe("Error calculating room price: " + e.getMessage());
            throw e;
        }
    }
}