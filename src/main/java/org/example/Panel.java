package org.example;

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
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    /**
     * Returns the number of floors in the property.
     *
     * @return the number of floors
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets the number of floors in the property.
     *
     * @param floor the number of floors to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Returns whether the property is insulated.
     *
     * @return true if the property is insulated; false otherwise
     */
    public boolean isInsulated() {
        return isInsulated;
    }

    /**
     * Sets the insulation status of the property.
     *
     * @param insulated true if the property is insulated; false otherwise
     */
    public void setInsulated(boolean insulated) {
        isInsulated = insulated;
    }

    /**
     * Calculates the total price of the property, including adjustments for floor level and insulation.
     * Overrides {@link RealEstate#getTotalPrice()}.
     *
     * @return the final total price
     */
    @Override
    public int getTotalPrice() {
        int price = super.getTotalPrice();
        if (floor >= 0 && floor <= 2) {
            price *= 1.05;
        } else if (floor == 10) {
            price *= 0.95;
        }

        if (isInsulated) price *= 1.05;

        return price;
    }

    /**
     * Returns a one-line description of the property.
     * Overrides {@link PanelInterface}.
     *
     * @return a brief description of the property
     */
    @Override
    public String toString() {
        return super.toString() +
                " is a Panel with " +
                "floor=" + floor +
                ", isInsulated=" + isInsulated;
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
        if (realEstate == null) return false;
        return realEstate.getTotalPrice() == this.getTotalPrice();
    }

    /**
     * Calculates the price per room.
     * Overrides {@link PanelInterface#roomPrice()}.
     *
     * @return the price of a single room, or 0 if there are no rooms
     */
    @Override
    public double roomPrice() {
        if (numberOfRooms != 0) {
            return super.price / numberOfRooms;
        }
        return 0;
    }
}