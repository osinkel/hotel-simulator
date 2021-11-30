package models;

import java.util.ArrayList;

public class Guest {
    int id;
    String name;
    int num;
    String checkInDate;
    String checkOutDate;
    ArrayList<OrderedService> serviceList=new ArrayList<OrderedService>();

    public  int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate=checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate=checkOutDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getName() {
        return name;
    }

    public void setNum(int num) {
        this.num=num;
    }

    public int getNum() {
        return num;
    }

    public ArrayList<OrderedService> getServiceList(){
        return serviceList;
    }
}
