package repository.impl;

import models.Service;
import repository.ServiceDao;

import java.util.ArrayList;

public class ServiceDaoImpl extends AbstractDaoImpl<Service> implements ServiceDao {
    ArrayList<Service> services = new ArrayList<Service>();

    @Override
    public boolean add(Service service) {
        try {
            this.services.add(service);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isExist(Service service){
        for(int i=0; i<services.size(); i++) {
            if(services.get(i).getName()==service.getName()) return true;
        }
        return false;
    }

    @Override
    public boolean searchAndChangeService(String name, int num) {
        for(int i=0; i<services.size(); i++) {
            if(services.get(i).getName() == name) {
                services.get(i).setPrice(num);
                return true;
            }
        }
        return false;
    }

    @Override
    public Service searchService(String name) {
        for(int i=0; i<services.size(); i++) {
            if(services.get(i).getName().equals(name)) {
                return services.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Service> getServices(){
        return services;
    }
}
