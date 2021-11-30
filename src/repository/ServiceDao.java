package repository;

import models.Service;

import java.util.ArrayList;

public interface ServiceDao extends GenericDao<Service> {
    boolean isExist(Service service);
    boolean searchAndChangeService(String name, int num);
    Service searchService(String name);
    ArrayList<Service> getServices();
}
