package ui.views;

import ui.commands.Command;

public abstract class MenuItem {

    private String title;
    private Command<?> command;

    public MenuItem(Command<?> command, String title) {
        this.command = command;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public abstract void doAction();
}
