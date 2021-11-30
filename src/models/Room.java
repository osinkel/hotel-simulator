package models;

import java.util.ArrayList;

public class Room {
    int id;
    int number;
    int price;
    int capacity;
    int stars;
    boolean isTaken;
    boolean isServiced;
    ArrayList<Guest> guests=new ArrayList<Guest>();

    public  int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken=isTaken;
    }

    public boolean getIsServiced() {
        return isServiced;
    }

    public void setIsServiced(boolean isServiced) {
        this.isServiced=isServiced;
    }

    public void setPrice(int price) {
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number=number;
    }

    public void setCapacity(int capacity) {
        this.capacity=capacity;
    }

    public void setStars(int stars) {
        this.stars=stars;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStars() {
        return stars;
    }

    public void setGuests(Guest guest) {
        guests.add(guest);
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }
}
