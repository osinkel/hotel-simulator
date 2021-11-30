package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.Room;
import models.Service;
import ui.commands.Command;

import java.util.Map;

public class AddServiceCmd implements Command<Service> {

    private final Facade facade;

    public AddServiceCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<Service> execute(Map<String, Object> params) {
        return facade.addService(params);
    }
}
