package GroceryOrderMgmtService.validators.orderValidationDecorators;

import GroceryOrderMgmtService.dao.WarehouseDao;
import GroceryOrderMgmtService.dao.WarehouseLocalDao;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.validators.IOrderValidator;

import java.util.List;

public class ItemAvailabilityDecorator extends OrderValidationDecorator {
    private WarehouseDao warehouseDao;
    public ItemAvailabilityDecorator(IOrderValidator orderValidator){
        super(orderValidator);
        this.warehouseDao = WarehouseLocalDao.getInstance();
    }

    public boolean validate(OrderRequest request) {
        if(!decoratedOrderValidator.validate(request))
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
