package ui.commands.impl;

import dto.OperationResult;
import facade.Facade;
import models.OrderedService;
import models.Room;
import ui.commands.Command;

import java.util.Map;

public class GuestServicesListCmd implements Command<OrderedService> {
    private final Facade facade;

    public GuestServicesListCmd(Facade facade) {
        this.facade = facade;
    }

    @Override
    public OperationResult<OrderedService> execute(Map<String, Object> params) {
        return facade.sortServicesOfGuest(params);
    }
}
