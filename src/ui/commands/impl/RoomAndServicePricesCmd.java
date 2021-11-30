package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.Room;
import ui.commands.Command;

import java.util.Map;

public class RoomAndServicePricesCmd implements Command<Object> {
    private final Facade facade;

    public RoomAndServicePricesCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Object> execute(Map<String, Object> params) {
        return facade.sortRoomsAndServices(params);
    }
}
