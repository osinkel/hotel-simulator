package models;

import java.util.ArrayList;

public class OrderedService {
    int id;
    Service service;
    String date;

    public  int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setService(Service service) {
        this.service=service;
    }

    public void setDate(String date) {
        this.date=date;
    }

    public Service getService() {
        return service;
    }

    public String getDate() {
        return date;
    }
}
