package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.Guest;
import models.Room;
import ui.commands.Command;

import java.util.Map;

public class LastRoomGuestsCmd implements Command<Guest> {
    private final Facade facade;

    public LastRoomGuestsCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Guest> execute(Map<String, Object> params) {
        return facade.lastGuestsOfRoom(params);
    }
}
