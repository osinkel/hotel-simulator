package services;

import models.OrderedService;
import models.Service;
import repository.ServiceDao;

import java.io.*;
import java.util.ArrayList;

public class ServiceService {
    private ServiceDao serviceDao;
    public ServiceService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public Service createService(String name, int price) {
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        return service;
    }

    public boolean addService(String name, int price) {
        Service service = createService(name, price);

        boolean isAlreadyExist=serviceDao.isExist(service);

        if(!isAlreadyExist) {
            if(serviceDao.getServices().size()!=0){
                service.setId(serviceDao.getServices().get(serviceDao.getServices().size()-1).getId()+1);
            }else {
                service.setId(1);
            }
            serviceDao.add(service);
        }

        return !isAlreadyExist;
    }

    public boolean changePrice(String name, int newPrice) {
        if(serviceDao.searchAndChangeService(name, newPrice)) return true;
        else return false;
    }

    public ServiceDao getDao(){
        return serviceDao;
    }

    public ArrayList<Service> getServices(){
        return  serviceDao.getServices();
    }

    public String export(Service service){
        File file=new File("ServiceData.csv");
        try (FileWriter writer = new FileWriter(file.getName(), true)) {
            StringBuilder sb = new StringBuilder();
            if(file.length()==0){
                sb.append("id");
                sb.append(',');
                sb.append("Name");
                sb.append(',');
                sb.append("Price");
                sb.append('\n');
            }
            sb.append(service.getId());
            sb.append(',');
            sb.append(service.getName());
            sb.append(',');
            sb.append(service.getPrice());
            sb.append('\n');

            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String export(OrderedService service){
        File file=new File("OrderedServiceData.csv");
        try (FileWriter writer = new FileWriter(file.getName(), true)) {
            StringBuilder sb = new StringBuilder();
            if(file.length()==0) {
                sb.append("id");
                sb.append(',');
                sb.append("ServiceId");
                sb.append(',');
                sb.append("Date");
                sb.append('\n');
            }
            sb.append(service.getId());
            sb.append(',');
            sb.append(service.getService().getId());
            sb.append(',');
            sb.append(service.getDate());
            sb.append('\n');
            writer.write(sb.toString());
        } catch (IOException e) {
            return e.getMessage();
        }
        return null;
    }

    public Service searchService(String name){
        return  serviceDao.searchService(name);
    }
}
