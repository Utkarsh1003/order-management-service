package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.dto.ItemRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<Date, Map<String, Integer>> dayWiseItemQuantityMap;

    public Warehouse() {
        this.dayWiseItemQuantityMap = new HashMap<>();
    }

    public Integer getItemQuantity(Date date, String itemId){
        return dayWiseItemQuantityMap.get(date).get(itemId);
    }

    public void updateItemQuantityMap(ItemRequest itemRequest, Date date){
        Map<String, Integer> itemQuantityMap = dayWiseItemQuantityMap.get(date);
        itemQuantityMap.put(itemRequest.getItemId(), itemQuantityMap.get(itemRequest.getItemId()) - itemRequest.getQuantity());
    }

    public void populateItemQuantityMap(Date date, Map<String, Integer> itemQuantityMap){
        dayWiseItemQuantityMap.put(date, itemQuantityMap);
    }
}
