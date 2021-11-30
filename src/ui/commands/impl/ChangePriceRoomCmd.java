package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.Room;
import models.Service;
import ui.commands.Command;

import java.util.Map;

public class ChangePriceRoomCmd implements Command<Room> {
    private final Facade facade;

    public ChangePriceRoomCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Room> execute(Map<String, Object> params) {
        return facade.changeRoomPrice(params);
    }
}
