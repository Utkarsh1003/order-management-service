package GroceryOrderMgmtService.config;

import java.util.Map;

public class WarehouseConfig {
    private String date;
    private Map<String, Integer> itemQuantityMap;

    public String getDate() {
        return date;
    }

    public Map<String, Integer> getItemQuantityMap() {
        return itemQuantityMap;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemQuantityMap(Map<String, Integer> itemQuantityMap) {
        this.itemQuantityMap = itemQuantityMap;
    }
}
