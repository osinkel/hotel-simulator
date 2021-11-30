package repository;

import models.Guest;
import models.OrderedService;
import models.Service;

import java.util.ArrayList;

public interface GuestDao extends GenericDao<Guest> {
    void addService(OrderedService service);
    int count();
    Guest search(String name);
    void sortByName();
    void sortByDate();
    boolean compareDates(String strDate1, String strDate2);
    ArrayList<Guest> getGuests();
}
