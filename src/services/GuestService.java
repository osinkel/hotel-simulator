package services;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.Guest;
import models.OrderedService;
import models.Room;
import models.Service;
import repository.GuestDao;
import repository.RoomDao;
import repository.ServiceDao;

public class GuestService {
    private GuestDao guestDao;

    public GuestService(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    int diffBetweenDate(String strDate1, String strDate2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(strDate1);
            Date date2 = dateFormat.parse(strDate2);
            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
            int diffDays = (int)(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS));
            return diffDays;
        } catch (ParseException e) {
            return -1;
        }
    }

    public Guest createGuest(String name, String checkOutDate, int numRoom) {
        if(isValidDate(checkOutDate)) {

            String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            if(guestDao.compareDates(currentDate, checkOutDate)) {
                Guest guest=new Guest();
                guest.setCheckInDate(currentDate);
                guest.setCheckOutDate(checkOutDate);
                guest.setName(name);
                guest.setNum(numRoom);
                return guest;
            }else return null;


        }else return null;
    }

    public Guest createGuest(int id, String name, String checkInDate, String checkOutDate, int numRoom) {
        if(isValidDate(checkOutDate)) {
            if(guestDao.compareDates(checkInDate, checkOutDate)) {
                Guest guest=new Guest();
                guest.setId(id);
                guest.setCheckInDate(checkInDate);
                guest.setCheckOutDate(checkOutDate);
                guest.setName(name);
                guest.setNum(numRoom);
                return guest;
            }else return null;


        }else return null;
    }


    public boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void sortServicesByPrice(ArrayList<OrderedService> list) {
        list.sort(new Comparator<OrderedService>() {
            @Override
            public int compare(OrderedService o1, OrderedService o2) {
                if (o1.getService().getPrice() == o2.getService().getPrice()) return 0;
                else if (o1.getService().getPrice()> o2.getService().getPrice()) return 1;
                else return -1;
            }
        });
    }

    public void sortServicesByDate(ArrayList<OrderedService> list) {
        list.sort(new Comparator<OrderedService>() {
            @Override
            public int compare(OrderedService o1, OrderedService o2) {
                if(guestDao.compareDates(o1.getDate(), o2.getDate())) return -1;
                else return 1;
            }
        });
    }

    public boolean orderService(String name, Room room, ServiceDao serviceDao) {
        Service service = serviceDao.searchService(name);
        if(service!=null){
            OrderedService orderedService=new OrderedService();
            if(room.getGuests().get(0).getServiceList().size()!=0){
                orderedService.setId(room.getGuests().get(0).getServiceList().get(room.getGuests().get(0).getServiceList().size()-1).getId()+1);
            }else {
                service.setId(1);
            }
            orderedService.setService(service);
            orderedService.setDate(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            guestDao.addService(orderedService);
            return true;
        }
        return false;
    }

    public int allGuests(){
        return guestDao.count();
    }

    public int allPriceGuest(Room room) {
        int days=diffBetweenDate(room.getGuests().get(0).getCheckInDate(), room.getGuests().get(0).getCheckOutDate());
        int priceService=0;
        for(int i=0; i<room.getGuests().get(0).getServiceList().size(); i++){
            priceService=priceService+room.getGuests().get(0).getServiceList().get(i).getService().getPrice();
        }
        return priceService+(days*room.getPrice());
    }

    public Guest searchGuest(String name){
        return guestDao.search(name);
    }

    public boolean addGuest(Guest guest) {

        if(guestDao.getGuests().size()!=0){
            guest.setId(guestDao.getGuests().get(guestDao.getGuests().size()-1).getId()+1);
        }else guest.setId(1);
        return guestDao.add(guest);
    }

    public void sortGuestsByName() {
        guestDao.sortByName();
    }

    public void sortGuestsByDate() {
        guestDao.sortByDate();
    }

    public ArrayList<Guest> getGuests(){
        return guestDao.getGuests();
    }

    public GuestDao getDao(){
        return guestDao;
    }

    public String exportData(Guest guest){
        StringBuilder sb2 = new StringBuilder();
        File file = new File("GuestData.csv");
        try (FileWriter writer = new FileWriter(file.getName(), true)) {
            StringBuilder sb = new StringBuilder();
            if(file.length()==0){
                sb.append("id");
                sb.append(',');
                sb.append("Name");
                sb.append(',');
                sb.append("RoomNum");
                sb.append(',');
                sb.append("CheckIn");
                sb.append(',');
                sb.append("CheckOut");
                sb.append(',');
                sb.append("ServicesId");
                sb.append('\n');
            }

            sb.append(guest.getId());
            sb.append(',');
            sb.append(guest.getName());
            sb.append(',');
            sb.append(guest.getNum());
            sb.append(',');
            sb.append(guest.getCheckInDate());
            sb.append(',');
            sb.append(guest.getCheckOutDate());
            sb.append(',');
            if(guest.getServiceList().size()!=0){
                for(int i=0; i<guest.getServiceList().size(); i++){
                    if(i!=guest.getServiceList().size()-1) {
                        sb2.append(guest.getServiceList().get(i).getId());
                        sb2.append('_');
                    }
                    else sb2.append(guest.getServiceList().get(i).getId());
                }
                sb.append(sb2);
            }
            sb.append('\n');
            writer.write(sb.toString());
        } catch (IOException e) {
            return null;
        }
        return sb2.toString();
    }
}
