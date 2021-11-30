package ui.context;

import dto.OperationResult;
import dto.OperationStatus;
import facade.Facade;
import models.*;
import ui.commands.impl.*;
import ui.services.CliService;
import ui.views.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuInitializer {
    private static MenuInitializer instance = null;
    private CliService cliService;

    public static MenuInitializer getInstance() {
        if (instance == null) {
            instance = new MenuInitializer();
        }
        return instance;
    }

    public CliService getCliService() {
        return this.cliService;
    }

    public void initialize(Facade facade) {
        Menu menu = new Menu();
        initMenuItems(menu, facade);
        this.cliService = new CliService(menu);
    }

    private void initMenuItems(Menu menu, Facade facade) {
        AccommodateRoomCmd accommodateRoomCmd = new AccommodateRoomCmd(facade);
        AddRoomCmd addRoomCmd = new AddRoomCmd(facade);
        AddServiceCmd addServiceCmd=new AddServiceCmd(facade);
        ChangePriceRoomCmd changePriceRoomCmd=new ChangePriceRoomCmd(facade);
        ChangePriceServiceCmd changePriceServiceCmd=new ChangePriceServiceCmd(facade);
        ChangeStatusRoomCmd changeStatusRoomCmd=new ChangeStatusRoomCmd(facade);
        CountFreeRoomsCmd countFreeRoomsCmd=new CountFreeRoomsCmd(facade);
        CountGuestsCmd countGuestsCmd=new CountGuestsCmd(facade);
        EvictRoomCmd evictRoomCmd=new EvictRoomCmd(facade);
        FreeRoomsAfterCmd freeRoomsAfterCmd=new FreeRoomsAfterCmd(facade);
        GuestPriceCmd guestPriceCmd=new GuestPriceCmd(facade);
        GuestServicesListCmd guestServicesListCmd=new GuestServicesListCmd(facade);
        GuestsListCmd guestsListCmd=new GuestsListCmd(facade);
        LastRoomGuestsCmd lastRoomGuestsCmd=new LastRoomGuestsCmd(facade);
        OrderServiceCmd orderServiceCmd=new OrderServiceCmd(facade);
        RoomAndServicePricesCmd roomAndServicePricesCmd=new RoomAndServicePricesCmd(facade);
        RoomDetailsCmd roomDetailsCmd=new RoomDetailsCmd(facade);
        RoomsListCmd roomsListCmd=new RoomsListCmd(facade);

        menu.addItem(1, new MenuItem(addRoomCmd, "Добавить номер") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число для номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                System.out.println("Введите цену номера:");
                fn = in.nextInt();
                params.put("price", fn);

                System.out.println("Введите вместимость номера:");
                fn = in.nextInt();
                params.put("capacity", fn);

                System.out.println("Введите число звезд номера:");
                fn = in.nextInt();
                params.put("stars", fn);

                OperationResult<Room> result = addRoomCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(2, new MenuItem(addServiceCmd, "Добавить услугу") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите название услуги:");
                String fn = in.nextLine();
                params.put("name", fn);

                System.out.println("Введите цену услуги:");
                Integer fn1 = in.nextInt();
                params.put("price", fn1);

                OperationResult<Service> result = addServiceCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(3, new MenuItem(addServiceCmd, "Изменить цену номера") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                System.out.println("Введите цену услуги:");
                fn = in.nextInt();
                params.put("newPrice", fn);

                OperationResult<Room> result = changePriceRoomCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(4, new MenuItem(addServiceCmd, "Изменить цену услуги") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите название услуги:");
                String fn = in.nextLine();
                params.put("name", fn);

                System.out.println("Введите цену услуги:");
                Integer fn1 = in.nextInt();
                params.put("newPrice", fn1);

                OperationResult<Service> result = changePriceServiceCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(5, new MenuItem(addServiceCmd, "Изменить статус номера") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);
                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                OperationResult<Room> result = changeStatusRoomCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(6, new MenuItem(addServiceCmd, "Поселить в номер") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите имя гостя:");
                String fn = in.nextLine();
                params.put("name", fn);

                System.out.println("Введите число номера:");
                Integer fn1 = in.nextInt();
                params.put("num", fn1);
                System.out.println("Введите дату выселения(dd/mm/yyyy):");
                fn = in.next();
                params.put("checkOutDate", fn);

                OperationResult<Room> result = accommodateRoomCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(7, new MenuItem(addServiceCmd, "Выселить из номера") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                OperationResult<Room> result = evictRoomCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(8, new MenuItem(addServiceCmd, "Список номеров") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);
                System.out.println("1.по цене 2.по вместимости 3.по звездам");
                System.out.println("Введите вариант сортировки:");
                Integer fn = in.nextInt();
                params.put("sortBy", fn);

                System.out.println("0.все 1.только свободные");
                System.out.println("Введите вариант сортировки:");
                Boolean fn1 = in.nextInt() != 0;
                params.put("onlyFree", fn1);

                OperationResult<Room> result = roomsListCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()==OperationStatus.SUCCESS){
                    System.out.println("№ Вместимость Цена   Звезды");
                    ArrayList<Room> list =result.getValues();
                    for(int i=0; i<list.size(); i++) {
                        System.out.print(list.get(i).getNumber() + "\t" +list.get(i).getCapacity()+"\t"+ list.get(i).getPrice()+"\t"+list.get(i).getStars()+"\n");
                    }
                }
                System.out.println();
            }
        });

        menu.addItem(9, new MenuItem(addServiceCmd, "Список гостей") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("1.по имени 2.по дате выселения");
                System.out.println("Введите вариант сортировки:");
                Integer fn = in.nextInt();
                params.put("sortBy", fn);

                OperationResult<Guest> result = guestsListCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()==OperationStatus.SUCCESS){
                    System.out.println("Имя\t\tДата выселения");
                    ArrayList<Guest> list = result.getValues();
                    for(int i=0; i<list.size(); i++) {
                        System.out.println(list.get(i).getName()+"\t"+list.get(i).getCheckOutDate());
                    }
                }
                System.out.println();
            }
        });

        menu.addItem(10, new MenuItem(addServiceCmd, "Количество свободных номеров") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                OperationResult<Room> result = countFreeRoomsCmd.execute(params);
                if(result.getStatus()==OperationStatus.SUCCESS) System.out.println(result.getMessage()+result.getValue());
                else System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(11, new MenuItem(addServiceCmd, "Количество гостей отеля") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                OperationResult<Guest> result = countGuestsCmd.execute(params);
                if(result.getStatus()==OperationStatus.SUCCESS) System.out.println(result.getMessage()+result.getValue());
                else System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(12, new MenuItem(addServiceCmd, "Список номеров свободных по определенной дате") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите дату(dd/mm/yyyy):");
                String fn = in.next();
                params.put("date", fn);

                OperationResult<Room> result = freeRoomsAfterCmd.execute(params);
                if(result.getStatus()==OperationStatus.SUCCESS){
                    System.out.println(result.getMessage()+result.getValue());
                    System.out.println("№ Вместимость Цена   Звезды");
                    ArrayList<Room> list=result.getValues();
                    for(int i=0; i<list.size(); i++) {
                        System.out.print(list.get(i).getNumber() + "\t" +list.get(i).getCapacity()+"\t"+ list.get(i).getPrice()+"\t"+list.get(i).getStars()+"\n");
                    }
                }else System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(13, new MenuItem(addServiceCmd, "Сумма оплаты за номер для гостя") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                OperationResult<Room> result = guestPriceCmd.execute(params);
                if(result.getStatus()==OperationStatus.SUCCESS){
                    System.out.println(result.getMessage()+result.getValue());
                }else System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(14, new MenuItem(addServiceCmd, "Посмотреть 3-х последних гостей номера и даты их пребывания") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                OperationResult<Guest> result = lastRoomGuestsCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()==OperationStatus.SUCCESS){
                    ArrayList<Guest> list=result.getValues();
                    System.out.println("Гость\t\t\tДата пребывания");
                    for(int i=0; i<list.size(); i++){
                        System.out.println(list.get(i).getName()+"\t\t\t"+list.get(i).getCheckInDate()+" - "+list.get(i).getCheckOutDate());
                    }
                }
                System.out.println();
            }
        });

        menu.addItem(15, new MenuItem(addServiceCmd, "Заказать услугу для гостя") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                System.out.println("Введите название услуги:");
                String fn1 = in.next();
                params.put("name", fn1);

                OperationResult<Room> result = orderServiceCmd.execute(params);
                System.out.println(result.getMessage());
                System.out.println();
            }
        });

        menu.addItem(16, new MenuItem(addServiceCmd, "Посмотреть список услуг гостя и их цену") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите имя гостя:");
                String fn = in.nextLine();
                params.put("name", fn);

                System.out.println("1.по цене 2.по дате заказа");
                System.out.println("Введите вариант сортировки:");
                Integer fn1 = in.nextInt();
                params.put("sortBy", fn1);

                OperationResult<OrderedService> result = guestServicesListCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()== OperationStatus.SUCCESS){
                    ArrayList<OrderedService> list=result.getValues();
                    System.out.println("Название\t\tЦена\t\tДата заказа");
                    for(int j=0; j<list.size(); j++) {
                        System.out.println(list.get(j).getService().getName()+"\t\t"+list.get(j).getService().getPrice()+"\t\t"+list.get(j).getDate());
                    }
                }
                System.out.println();
            }
        });

        menu.addItem(17, new MenuItem(addServiceCmd, "Цены услуг и номеров") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("1.по цене 2.по разделу");
                System.out.println("Введите вариант сортировки:");
                Integer fn = in.nextInt();
                params.put("sortBy", fn);

                OperationResult<Object> result = roomAndServicePricesCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()==OperationStatus.SUCCESS){
                    ArrayList<Object> list = result.getValues();
                    System.out.println("Имя/Номер\t\tЦена\t\tРаздел");
                    for(int i=0; i<list.size(); i++) {
                        if(list.get(i) instanceof Room) {
                            Room room = (Room) list.get(i);
                            System.out.println("\t"+room.getNumber() +"\t\t"+room.getPrice()+"\t\t"+room.getClass().getSimpleName());
                        }else {
                            Service service=(Service)list.get(i);
                            System.out.println(service.getName() +"\t\t"+service.getPrice()+"\t\t"+service.getClass().getSimpleName());
                        }
                    }
                }

                System.out.println();
            }
        });

        menu.addItem(18, new MenuItem(addServiceCmd, "Посмотреть детали отдельного номера") {
            @Override
            public void doAction() {
                Map<String, Object> params = new HashMap<>();
                Scanner in = new Scanner(System.in);

                System.out.println("Введите число номера:");
                Integer fn = in.nextInt();
                params.put("num", fn);

                OperationResult<Room> result = roomDetailsCmd.execute(params);
                System.out.println(result.getMessage());
                if(result.getStatus()==OperationStatus.SUCCESS){
                    Room room=result.getValues().get(0);
                    System.out.println("Номер: "+room.getNumber()+" Цена: "+room.getPrice()+" Вместимость: "+room.getCapacity()+" Звезды: "+room.getStars());
                    if(room.getIsTaken()) {
                        System.out.println("Гость: "+room.getGuests().get(0).getName()+" Дата заезда: "+room.getGuests().get(0).getCheckInDate()+" Дата выезда:"+ room.getGuests().get(0).getCheckOutDate());
                        if(room.getGuests().get(0).getServiceList().size()!=0) {
                            System.out.print("Заказанные услуги:");
                            for(int i=0; i<room.getGuests().get(0).getServiceList().size(); i++) {
                                System.out.print(" "+room.getGuests().get(0).getServiceList().get(i).getService().getName()+"(Цена: "+room.getGuests().get(0).getServiceList().get(i).getService().getPrice()+" Дата: "+room.getGuests().get(0).getServiceList().get(i).getDate()+")");
                            }
                            System.out.println();
                        }

                    }else if(!room.getIsServiced()) {
                        System.out.println("Номер на ремонте");
                    }else  System.out.println("Номер свободен");
                }

                System.out.println();
            }
        });
    }
}
