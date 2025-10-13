package org.example;

public class Panel extends RealEstate implements PanelInterface{
    int floor;
    boolean isInsulated;


    public Panel(){
        super();
    }
    public Panel(String city, double price, double sqm, int numberOfRooms, Genre genre, int floor,
                 boolean isInsulated){
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isInsulated() {
        return isInsulated;
    }

    public void setInsulated(boolean insulated) {
        isInsulated = insulated;
    }

    @Override
    public int getTotalPrice(){
        int price = super.getTotalPrice();
        if(floor >= 0 && floor <= 2){
            price*=1.05;
        }
        else if (floor == 10){
            price *= 0.95;
        }

        if(isInsulated) price *= 1.05;

        return price;
    }


    @Override
    public String toString() {
        return
                super.toString()+
                " is a Panel with" +
                "floor=" + floor +
                ", isInsulated=" + isInsulated ;
    }

    @Override
    public boolean hasSameAmount(RealEstate realEstate) {
        if (realEstate == null)return false;

        return realEstate.getTotalPrice() == this.getTotalPrice();
    }

    @Override
    public double roomPrice() {
        if(numberOfRooms != 0){
            return super.price/numberOfRooms;
        }
        return 0;
    }
}
