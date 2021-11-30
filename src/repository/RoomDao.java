package repository;

import models.Room;

import java.util.ArrayList;

public interface RoomDao extends GenericDao<Room> {
    Room search(int num);
    boolean isExist(int num);
    boolean searchAndChange(int num, int price);
    void sortByCapacity();
    void sortByStars();
    void sortByPrice();
    ArrayList<Room> getRooms();
}
