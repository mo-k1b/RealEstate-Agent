package org.example;

public class Main {
    public static void main(String[] args) {


        RealEstate estate1 = new RealEstate();
        estate1.price= 4000;
        estate1.city = "Nyiregyhaza";
        estate1.genre = RealEstate.Genre.FAMILYHOUSE;
        estate1.numberOfRooms = 3;
        estate1.sqm = 20;

        System.out.println("Before Discount: "+estate1.toString());
        estate1.makeDiscount(10);
        System.out.println("After Discount: "+estate1.toString());
        System.out.println("Total Price: "+estate1.getTotalPrice());
        System.out.println("Avg Sqm per room: "+estate1.averageSqmPerRoom());


    }
}