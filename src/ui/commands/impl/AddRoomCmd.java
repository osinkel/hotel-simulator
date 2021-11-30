package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.Room;
import ui.commands.Command;

import java.util.Map;

public class AddRoomCmd implements Command<Room> {

    private final Facade facade;

    public AddRoomCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Room> execute(Map<String, Object> params) {
        return facade.addRoom(params);
    }
}
