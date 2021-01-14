package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dao.WarehouseDao;
import GroceryOrderMgmtService.dao.WarehouseLocalDao;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;

import java.util.List;

public class ValidateItemAvailability extends OrderValidatorDecorator {
    private WarehouseDao warehouseDao;
    public ValidateItemAvailability(IOrderValidator orderValidator){
        super(orderValidator);
        this.warehouseDao = WarehouseLocalDao.getInstance();
    }

    public boolean validate(OrderRequest request) {
        if(!super.validate(request))
            return false;

        List<ItemRequest> items = request.getItems();

        for(ItemRequest itemRequest:items) {
            Integer itemQuantity = warehouseDao.getItemQuantity(request.getWarehouseId(), request.getDeliveryDate(), itemRequest.getItemId());
            if(itemQuantity == null || itemQuantity <= itemRequest.getQuantity())
                return false;
        }

        return true;
    }
}
