package repository.impl;

import models.Room;
import repository.RoomDao;

import java.util.ArrayList;
import java.util.Comparator;

public class RoomDaoImpl extends AbstractDaoImpl<Room> implements RoomDao {
    ArrayList<Room> rooms = new ArrayList<Room>();

    @Override
    public boolean add(Room room) {
        try {
            this.rooms.add(room);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Room search(int num) {
        for(int i=0; i<rooms.size(); i++) {
            if(rooms.get(i).getNumber() == num) {
                return rooms.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean isExist(int num){
        for(int i=0; i<rooms.size(); i++) {
            if(rooms.get(i).getNumber()==num) return true;
        }
        return false;
    }

    @Override
    public boolean searchAndChange(int num, int price) {
        for(int i=0; i<rooms.size(); i++) {
            if(rooms.get(i).getNumber() == num) {
                rooms.get(i).setPrice(price);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sortByPrice() {
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.getPrice() == o2.getPrice()) return 0;
                else if (o1.getPrice()> o2.getPrice()) return 1;
                else return -1;
            }
        });
    }

    @Override
    public void sortByCapacity() {
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.getCapacity() == o2.getCapacity()) return 0;
                else if (o1.getCapacity()> o2.getCapacity()) return 1;
                else return -1;
            }
        });
    }

    @Override
    public void sortByStars() {
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.getStars() == o2.getStars()) return 0;
                else if (o1.getStars()> o2.getStars()) return 1;
                else return -1;
            }
        });
    }

    @Override
    public ArrayList<Room> getRooms(){
        return rooms;
    }
}
