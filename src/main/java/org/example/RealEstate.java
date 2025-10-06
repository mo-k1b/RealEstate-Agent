package org.example;

public class RealEstate implements PropertyInterface{
    String city;
    double price;
    double sqm;
    int numberOfRooms;



    public enum Genre  {FAMILYHOUSE, CONDOMINIUM, FARM};
    Genre genre;



    public RealEstate(){
    }

    public RealEstate(String city, double price, double sqm, int numberOfRooms, Genre genre){
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSqm() {
        return sqm;
    }

    public void setSqm(double sqm) {
        this.sqm = sqm;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void makeDiscount(double percentage) {
        this.price = price - (price * (percentage/100));
    }

    @Override
    public int getTotalPrice() {
        switch (city){
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

    @Override
    public double averageSqmPerRoom() {
        return (numberOfRooms != 0)? sqm / numberOfRooms : 0;
    }

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
