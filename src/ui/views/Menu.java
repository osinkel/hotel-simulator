package ui.views;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    Map<Integer, MenuItem> items = new LinkedHashMap<>();

    public void addItem(Integer code, MenuItem item) {
        items.put(code, item);
    }

    public MenuItem getItem(Integer code) {
        MenuItem item = items.get(code);
        return item;
    }

    public Map<Integer, MenuItem> getItems() {
        return this.items;
    }
}
