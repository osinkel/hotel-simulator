package repository.impl;

import models.Guest;
import models.OrderedService;
import repository.GuestDao;
import services.GuestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class GuestDaoImpl extends AbstractDaoImpl<Guest> implements GuestDao {
    ArrayList<Guest> guests = new ArrayList<Guest>();

    @Override
    public boolean add(Guest guest) {
        try {
            this.guests.add(guest);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addService(OrderedService service){
        guests.get(0).getServiceList().add(service);
    }

    @Override
    public int count(){
        return guests.size();
    }

    @Override
    public Guest search(String name){
        for(int i=0; i<guests.size(); i++) {
            if(guests.get(i).getName().equals(name)) {
                return guests.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean compareDates(String strDate1, String strDate2){
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(strDate1);
            Date date2 = dateFormat.parse(strDate2);
            if(date2.after(date1)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void sortByName() {
        guests.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
    }

    @Override
    public void sortByDate() {
        guests.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if(compareDates(o1.getCheckOutDate(), o2.getCheckOutDate())) return -1;
                else return 1;
            }
        });
    }

    @Override
    public ArrayList<Guest> getGuests(){
        return guests;
    }
}
