package models;

public class Service {
    int id;
    String name;
    int price;

    public  int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setPrice(int price) {
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getPrice() {
        return price;
    }

}
