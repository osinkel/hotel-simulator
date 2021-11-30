package services;

import models.Guest;
import models.Room;
import repository.GuestDao;
import repository.RoomDao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RoomService {
    private RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public boolean accommodateInRoom(Room room, Guest guest) {
        if(!room.getIsTaken() && room.getIsServiced()) {
            room.setGuests(guest);
            room.setIsTaken(true);
            return true;
        } else return false;
    }

    public boolean evictFromRoom(Room room) {
        if(room.getIsTaken()) {
            room.setIsTaken(false);
            return true;
        } else return false;
    }

    public Room createRoom(int num, int price, int capacity, int stars) {
        Room room = new Room();
        room.setNumber(num);
        room.setPrice(price);
        room.setCapacity(capacity);
        room.setStars(stars);
        room.setIsTaken(false);
        room.setIsServiced(true);
        return room;
    }

    public Room createRoom(int id, int num, int price, int capacity, int stars) {
        Room room = new Room();
        room.setId(id);
        room.setNumber(num);
        room.setPrice(price);
        room.setCapacity(capacity);
        room.setStars(stars);
        room.setIsTaken(false);
        room.setIsServiced(true);
        return room;
    }

    public boolean addRoom(int num, int price, int capacity, int stars) {
        Room room = createRoom(num, price, capacity, stars);
        boolean isAlreadyExist=roomDao.isExist(room.getNumber());
        if(!isAlreadyExist) {
            if(roomDao.getRooms().size()!=0){
                room.setId(roomDao.getRooms().get(roomDao.getRooms().size()-1).getId()+1);
            }else {
                room.setId(1);
            }
            roomDao.add(room);

        }
        return !isAlreadyExist;
    }

    public Room searchRoom(int num){
        return roomDao.search(num);
    }

    public boolean changePrice(int num, int price){
        if(roomDao.searchAndChange(num, price)) return true;
        else return false;
    }

    public void sortRoomsByPrice() {
        roomDao.sortByPrice();
    }

    public void sortRoomsByCapacity() {
        roomDao.sortByCapacity();
    }

    public void sortRoomsByStars() {
        roomDao.sortByStars();
    }

    public ArrayList<Room> getRooms(){
        return roomDao.getRooms();
    }

    public int allFreeRooms() {
        int count=0;
        for(int i=0; i<roomDao.getRooms().size(); i++) {
            if(!roomDao.getRooms().get(i).getIsTaken()) count++;
        }
        return count;
    }

    public String exportData(Room room){
        StringBuilder sb2 = new StringBuilder();
        File file = new File("RoomData.csv");
        try (FileWriter writer = new FileWriter(file.getName(), true)) {
            StringBuilder sb = new StringBuilder();
            if(file.length()==0){
                sb.append("id");
                sb.append(',');
                sb.append("Number");
                sb.append(',');
                sb.append("Price");
                sb.append(',');
                sb.append("Capacity");
                sb.append(',');
                sb.append("Stars");
                sb.append(',');
                sb.append("IsTaken");
                sb.append(',');
                sb.append("IsServiced");
                sb.append(',');
                sb.append("GuestsId");
                sb.append('\n');
            }

            sb.append(room.getId());
            sb.append(',');
            sb.append(room.getNumber());
            sb.append(',');
            sb.append(room.getPrice());
            sb.append(',');
            sb.append(room.getCapacity());
            sb.append(',');
            sb.append(room.getStars());
            sb.append(',');
            sb.append(room.getIsTaken());
            sb.append(',');
            sb.append(room.getIsServiced());
            sb.append(',');
            if(room.getGuests().size()!=0){
                for(int i=0; i<room.getGuests().size(); i++){
                    if(i!=room.getGuests().size()-1) sb2.append(room.getGuests().get(i).getId()+'_');
                    else sb2.append(room.getGuests().get(i).getId());
                }

                sb.append(sb2);
            }
            sb.append('\n');

            writer.write(sb.toString());
        } catch (IOException e) {
            return e.getMessage();
        }
        return sb2.toString();
    }

    public ArrayList<Room> importData(){
        ArrayList<Room> list=new ArrayList<Room>();
        StringBuilder sb=new StringBuilder();
        try(FileReader reader = new FileReader("RoomData.csv"))
        {
            boolean startWriteRoom=false;
            int ch;
            while((ch=reader.read())!=-1){
                if(startWriteRoom){
                    if(ch=='\n'){
                        String str=sb.toString();
                        ArrayList<Object> dataRow=new ArrayList<Object>();
                        for (String retval : str.split(",")) {
                            dataRow.add(retval);
                        }
                        if(dataRow.size()==8){
                            Room room= createRoom(Integer.parseInt((String) dataRow.get(0)),Integer.parseInt((String)dataRow.get(1)),Integer.parseInt((String)dataRow.get(2)),Integer.parseInt((String)dataRow.get(3)),Integer.parseInt((String)dataRow.get(4)));
                            room.setIsTaken(Boolean.parseBoolean((String) dataRow.get(5)));
                            room.setIsServiced(Boolean.parseBoolean((String) dataRow.get(6)));
                            ArrayList<Integer> guestId=new ArrayList<Integer>();
                            for (String retval2 : dataRow.get(7).toString().split("_")) {
                                guestId.add(Integer.parseInt(retval2));
                            }
                            dataRow.add(guestId);
                        }else return null;
                    }
                }
                if(ch=='\n') startWriteRoom=true;
                sb.append((char)ch);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return list;
    }
}
