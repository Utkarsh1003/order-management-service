package GroceryOrderMgmtService.dao;

import java.util.Date;

public interface WarehouseDao {
    Warehouse getWarehouse(String id);
    Integer getItemQuantity(String warehouseId, Date date, String itemId);
}
