package dto;

import java.util.ArrayList;

public class OperationResult<T> {
    private OperationStatus status;
    private String message;
    private String message2;
    private ArrayList<T> values;
    private String value;

    public OperationStatus getStatus() {
        return status;
    }
    public OperationResult<T> setStatus(OperationStatus status) {
        this.status = status;
        return this;
    }
    public String getMessage() {
        return message;
    }

    public OperationResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }
    public OperationResult<T> setMessage2(String message2) {
        this.message2 = message2;
        return this;
    }
    public String getMessage2() {
        return message2;
    }

    public ArrayList<T> getValues() {
        return values;
    }

    public OperationResult<T> setValues(ArrayList<T> values) {
        this.values = values;
        return this;
    }
    public String getValue(){
        return value;
    }

    public OperationResult<T> setValue(String value){
        this.value=value;
        return this;
    }
}
