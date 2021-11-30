package context;

import facade.Facade;
import models.Guest;
import repository.GuestDao;
import repository.RoomDao;
import repository.ServiceDao;
import repository.impl.GuestDaoImpl;
import repository.impl.RoomDaoImpl;
import repository.impl.ServiceDaoImpl;
import services.GuestService;
import services.RoomService;
import services.ServiceService;

public class Context {
    private static Context instance = null;
    private Facade facade;

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public void initialize() {
        GuestDao guestDao = new GuestDaoImpl();
        GuestService guestService = new GuestService(guestDao);
        RoomDao roomDao=new RoomDaoImpl();
        RoomService roomService=new RoomService(roomDao);
        ServiceDao serviceDao=new ServiceDaoImpl();
        ServiceService serviceService=new ServiceService(serviceDao);
        this.facade = new Facade(guestService, roomService, serviceService);
    }

    public Facade getFacade() {
        return facade;
    }
}
