package ui.services;

import ui.views.Menu;
import ui.views.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CliService {

    private Menu menu;

    public CliService(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                Integer choice = in.nextInt();
                MenuItem item = menu.getItem(choice);
                if (item == null) {
                    printErrorChoice();
                    continue;
                }
                item.doAction();

            } catch (InputMismatchException e) {
                printErrorChoice();
                start();
            }
        }
    }

    public void printMenu() {
        System.out.println("Введите номер команды");
        menu.getItems().forEach((code, item) -> {
            System.out.println(" " + code + " - " + item.getTitle());
        });
    }

    public void printErrorChoice() {
        System.out.println("Команда не найдена\n");
    }
}
