package ui.commands;

import dto.OperationResult;

import java.util.Map;

public interface Command<R> {

    OperationResult<R> execute(Map<String, Object> params);

}
