package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dao.WarehouseDao;
import GroceryOrderMgmtService.dao.WarehouseLocalDao;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.dao.Warehouse;

import java.util.List;
import java.util.Map;

public class ValidateItemAvailability implements OrderValidator {
    private static ValidateItemAvailability instance;

    public static ValidateItemAvailability getInstance(){
        if(instance == null)
            instance = new ValidateItemAvailability();

        return instance;
    }

    private WarehouseDao warehouseDao;
    private ValidateItemAvailability(){
        this.warehouseDao = WarehouseLocalDao.getInstance();
    }

    public boolean validate(OrderRequest request) {
        List<ItemRequest> items = request.getItems();

        for(ItemRequest itemRequest:items) {
            Integer itemQuantity = warehouseDao.getItemQuantity(request.getWarehouseId(), request.getDeliveryDate(), itemRequest.getItemId());
            if(itemQuantity == null || itemQuantity <= itemRequest.getQuantity())
                return false;
        }

        return true;
    }
}
