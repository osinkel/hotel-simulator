package facade;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Map;

import dto.OperationResult;
import dto.OperationStatus;
import models.Guest;
import models.OrderedService;
import models.Room;
import models.Service;
import services.GuestService;
import services.RoomService;
import services.ServiceService;
import utils.LocalizationUtils;

public class Facade {

    RoomService roomService;
    ServiceService serviceService;
    GuestService guestService;

    public Facade(GuestService guestService, RoomService roomService, ServiceService serviceService) {
        this.guestService= guestService;
        this.roomService = roomService;
        this.serviceService = serviceService;
    }

    public OperationResult<Room> addRoom(Map<String, Object> params) {
        OperationResult<Room> result = null;
        if(roomService.addRoom((Integer)params.get("num"), (Integer)params.get("price"), (Integer)params.get("capacity"), (Integer)params.get("stars"))) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(1))
                    .setStatus(OperationStatus.SUCCESS);
        } else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(2))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Service> addService(Map<String, Object> params) {
        OperationResult<Service> result = null;
        if(serviceService.addService((String)params.get("name"), (Integer)params.get("price"))) {
            result = new OperationResult<Service>()
                    .setMessage(LocalizationUtils.localize(3))
                    .setStatus(OperationStatus.SUCCESS);
        } else result = new OperationResult<Service>()
                .setMessage(LocalizationUtils.localize(4))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Room> accommodateInRoom(Map<String, Object> params) {
        OperationResult<Room> result = null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        Guest guest=guestService.createGuest((String) params.get("name"), (String) params.get("checkOutDate"), (Integer) params.get("num"));
        if(guest!=null) {
            if(roomService.accommodateInRoom(room, guest)) {
                guestService.addGuest(guest);
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(6))
                        .setStatus(OperationStatus.SUCCESS);
            } else result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(7))
                    .setStatus(OperationStatus.ERROR);
        }else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(8))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Room> evictFromRoom(Map<String, Object> params) {
        OperationResult<Room> result = null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        if(roomService.evictFromRoom(room)) {
            guestService.getGuests().remove(room);
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(36))
                    .setStatus(OperationStatus.SUCCESS);
        } else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(9))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public  OperationResult<Room> changeRoomPrice(Map<String, Object> params) {
        OperationResult<Room> result = null;
        if(roomService.changePrice((Integer)params.get("num"), (Integer) params.get("newPrice"))){
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(10))
                    .setStatus(OperationStatus.SUCCESS);
        }
        else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(11))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Service> changeServicePrice(Map<String, Object> params) {
        OperationResult<Service> result = null;
        if(serviceService.changePrice((String) params.get("name"), (Integer) params.get("newPrice"))){
            result = new OperationResult<Service>()
                    .setMessage(LocalizationUtils.localize(12))
                    .setStatus(OperationStatus.SUCCESS);
        } else result = new OperationResult<Service>()
                .setMessage(LocalizationUtils.localize(13))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Room> changeRoomStatus(Map<String, Object> params) {
        OperationResult<Room> result = null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        if(room!=null) {
            if(!room.getIsTaken()){
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(37))
                        .setStatus(OperationStatus.SUCCESS);
            }else result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(29))
                    .setStatus(OperationStatus.ERROR);
        }
        return result;
    }

    public OperationResult<Room> getSortedRooms(Map<String, Object> params) {
        OperationResult<Room> result = null;
        switch((Integer) params.get("sortBy")) {
            case 1:{
                roomService.sortRoomsByPrice();
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(14))
                        .setStatus(OperationStatus.SUCCESS)
                        .setValues(roomService.getRooms());
                if((Boolean) params.get("onlyFree")){
                    ArrayList<Room> list=new ArrayList<Room>();
                    for (int i=0; i<roomService.getRooms().size(); i++){
                        if(!roomService.getRooms().get(i).getIsTaken()) list.add(roomService.getRooms().get(i));
                    }
                    result.setValues(list);
                }
                break;
            }
            case 2:{
                roomService.sortRoomsByCapacity();
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(15))
                        .setStatus(OperationStatus.SUCCESS)
                        .setValues(roomService.getRooms());
                if((Boolean) params.get("onlyFree")){
                    ArrayList<Room> list=new ArrayList<Room>();
                    for (int i=0; i<roomService.getRooms().size(); i++){
                        if(!roomService.getRooms().get(i).getIsTaken()) list.add(roomService.getRooms().get(i));
                    }
                    result.setValues(list);
                }
                break;
            }
            case 3:{
                roomService.sortRoomsByStars();
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(16))
                        .setStatus(OperationStatus.SUCCESS)
                        .setValues(roomService.getRooms());
                if((Boolean) params.get("onlyFree")){
                    ArrayList<Room> list=new ArrayList<Room>();
                    for (int i=0; i<roomService.getRooms().size(); i++){
                        if(!roomService.getRooms().get(i).getIsTaken()) list.add(roomService.getRooms().get(i));
                    }
                    result.setValues(list);
                }
                break;
            }
        }
        return result;
    }

    public OperationResult<Guest> getSortedGuests(Map<String, Object> params) {
        OperationResult<Guest> result = null;
        switch((Integer) params.get("sortBy")) {
            case 1:{
                guestService.sortGuestsByName();
                result = new OperationResult<Guest>()
                        .setMessage(LocalizationUtils.localize(17))
                        .setStatus(OperationStatus.SUCCESS)
                        .setValues(guestService.getGuests());
                break;
            }
            case 2:{
                guestService.sortGuestsByDate();
                result = new OperationResult<Guest>()
                        .setMessage(LocalizationUtils.localize(18))
                        .setStatus(OperationStatus.SUCCESS)
                        .setValues(guestService.getGuests());
                break;
            }
        }

        return result;
    }

    public OperationResult<Room> allFreeRooms() {
        OperationResult<Room> result=null;
        result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(19))
                .setStatus(OperationStatus.SUCCESS)
                .setValue(String.valueOf(roomService.allFreeRooms()));
        return result;
    }

    public OperationResult<Guest> allGuests() {
        OperationResult<Guest> result=null;
        result = new OperationResult<Guest>()
                .setMessage(LocalizationUtils.localize(20))
                .setStatus(OperationStatus.SUCCESS)
                .setValue(String.valueOf(guestService.allGuests()));
        return result;
    }

    public OperationResult<Room> freeRoomsAfter(Map<String, Object> params) {
        OperationResult<Room> result = null;
        ArrayList<Room> list = new ArrayList<Room>();
        String date=(String) params.get("date");
        if(guestService.isValidDate(date)) {
            String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            if(guestService.getDao().compareDates(currentDate, date)) {
                list = new ArrayList<Room>();
                for(int i=0; i<guestService.getGuests().size(); i++) {
                    if(guestService.getDao().compareDates(guestService.getGuests().get(i).getCheckOutDate(),date)) {
                        for(int j=0; j<roomService.getRooms().size(); j++) {
                            if(roomService.getRooms().get(j).getNumber()==guestService.getGuests().get(i).getNum()) list.add(roomService.getRooms().get(j));
                            else if(!roomService.getRooms().get(j).getIsTaken()) list.add(roomService.getRooms().get(j));
                        }
                    }
                }
            }
        }
        if(list!=null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(38))
                    .setStatus(OperationStatus.SUCCESS)
                    .setValue(date)
                    .setValues(list);
        } else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(21))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Room> allPriceGuest(Map<String, Object> params) {
        OperationResult<Room> result=null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        if(room.getIsTaken()){
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(23))
                    .setValue(String.valueOf(guestService.allPriceGuest(room)))
                    .setStatus(OperationStatus.SUCCESS);
        }else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(22))
                .setStatus(OperationStatus.ERROR);
        return result;
    }

    public OperationResult<Guest> lastGuestsOfRoom(Map<String, Object> params) {
        OperationResult<Guest> result=null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Guest>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        ArrayList<Guest> list = new ArrayList<Guest>();
        for(int i=0; i<room.getGuests().size(); i++) {
            if(i<3) {
                list.add(room.getGuests().get(i));
            }
        }
        result = new OperationResult<Guest>()
                .setMessage(LocalizationUtils.localize(32))
                .setStatus(OperationStatus.SUCCESS)
                .setValues(list);
        return result;

    }

    public OperationResult<Room> orderService(Map<String, Object> params) {
        OperationResult<Room> result=null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        if(room.getIsTaken()){
            if(guestService.orderService((String) params.get("name"), room, serviceService.getDao())) {
                result = new OperationResult<Room>()
                        .setMessage(LocalizationUtils.localize(24))
                        .setStatus(OperationStatus.SUCCESS);
            }else result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(25))
                    .setStatus(OperationStatus.ERROR);
        }else result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(26))
                .setStatus(OperationStatus.ERROR);
        return  result;
    }

    public OperationResult<OrderedService> sortServicesOfGuest(Map<String, Object> params){
        OperationResult<OrderedService> result=null;
        Guest guest = guestService.searchGuest((String) params.get("name"));
        if(guest!=null){
            switch ((Integer)params.get("sortBy")) {
                case 1: {
                    guestService.sortServicesByPrice(guest.getServiceList());
                    result = new OperationResult<OrderedService>()
                            .setMessage(LocalizationUtils.localize(30))
                            .setStatus(OperationStatus.SUCCESS)
                            .setValues(guest.getServiceList());
                    break;
                }
                case 2:{
                    guestService.sortServicesByDate(guest.getServiceList());
                    result = new OperationResult<OrderedService>()
                            .setMessage(LocalizationUtils.localize(31))
                            .setStatus(OperationStatus.SUCCESS)
                            .setValues(guest.getServiceList());
                    break;
                }
            }
        }else result = new OperationResult<OrderedService>()
                .setMessage(LocalizationUtils.localize(26))
                .setStatus(OperationStatus.ERROR);
        return  result;
    }

    public OperationResult<Object> sortRoomsAndServices(Map<String, Object> params) {
        OperationResult<Object> result=null;
        ArrayList<Object> list=new ArrayList<Object>();
        for(int i=0; i<roomService.getRooms().size(); i++) {
            list.add((Room)roomService.getRooms().get(i));
        }
        for(int i=0; i<serviceService.getServices().size(); i++) {
            list.add((Service)serviceService.getServices().get(i));
        }
        if(list.size()!=0){
            switch((Integer)params.get("sortBy")) {
                case 1:{
                    list=sortRoomsAndServicesByPrice(list);
                    result = new OperationResult<Object>()
                            .setMessage(LocalizationUtils.localize(40))
                            .setValues(list)
                            .setStatus(OperationStatus.SUCCESS);
                    break;
                }
                case 2:{
                    list=sortRoomsAndServicesBySection(list);
                    result = new OperationResult<Object>()
                            .setMessage(LocalizationUtils.localize(39))
                            .setValues(list)
                            .setStatus(OperationStatus.SUCCESS);
                    break;
                }
            }
        }
        return  result;
    }

    public ArrayList<Object> sortRoomsAndServicesByPrice(ArrayList<Object> list) {
        list.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Room && o2 instanceof Room) {
                    if (((Room) o1).getPrice() == ((Room) o2).getPrice()) return 0;
                    else if (((Room) o1).getPrice() > ((Room) o2).getPrice()) return 1;
                    else return -1;
                }else if(o1 instanceof Room && o2 instanceof Service) {
                    if (((Room) o1).getPrice() == ((Service) o2).getPrice()) return 0;
                    else if (((Room) o1).getPrice() > ((Service) o2).getPrice()) return 1;
                    else return -1;
                }else if((o1 instanceof Service && o2 instanceof Room)) {
                    if (((Service) o1).getPrice() == ((Room) o2).getPrice()) return 0;
                    else if (((Service) o1).getPrice() > ((Room) o2).getPrice()) return 1;
                    else return -1;
                }else {
                    if (((Service) o1).getPrice() == ((Service) o2).getPrice()) return 0;
                    else if (((Service) o1).getPrice() > ((Service) o2).getPrice()) return 1;
                    else return -1;
                }
            }
        });
        return list;
    }

    public ArrayList<Object> sortRoomsAndServicesBySection(ArrayList<Object> list){
        list.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
            }
        });
        return list;
    }

    public OperationResult<Room> roomInfo(Map<String, Object> params) {
        OperationResult<Room> result = null;
        Room room = roomService.searchRoom((Integer) params.get("num"));
        if(room==null) {
            result = new OperationResult<Room>()
                    .setMessage(LocalizationUtils.localize(5))
                    .setStatus(OperationStatus.ERROR);
            return result;
        }
        ArrayList<Room> list = new ArrayList<Room>();
        list.add(room);
        result = new OperationResult<Room>()
                .setMessage(LocalizationUtils.localize(41))
                .setValues(list)
                .setStatus(OperationStatus.SUCCESS);
        return result;
    }

}
