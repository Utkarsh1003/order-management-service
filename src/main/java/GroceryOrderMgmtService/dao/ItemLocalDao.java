package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.dto.Item;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemLocalDao implements ItemDao{
    private static ItemLocalDao instance;
    public static ItemLocalDao getInstance(){
        if(instance == null)
            instance = new ItemLocalDao();

        return instance;
    }

    private Map<String, Item> itemMap;
    private ItemLocalDao(){}

    @Override
    public Item getItemInfo(String id) {
        return itemMap.get(id);
    }

    public void populateData(List<Item> items){
        itemMap = items.stream().collect(Collectors.toMap(Item::getItemId, Function.identity()));
    }
}
