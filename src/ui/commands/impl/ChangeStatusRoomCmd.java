package ui.commands.impl;

import com.ibm.java.diagnostics.utils.commands.ICommand;
import dto.OperationResult;
import facade.Facade;
import models.Room;
import ui.commands.Command;

import java.util.Map;

public class ChangeStatusRoomCmd implements Command<Room> {
    private final Facade facade;

    public ChangeStatusRoomCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Room> execute(Map<String, Object> params) {
        return facade.changeRoomStatus(params);
    }
}
