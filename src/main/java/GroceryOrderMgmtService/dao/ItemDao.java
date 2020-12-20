package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.dto.Item;

public interface ItemDao {
    Item getItemInfo(String id);
}
