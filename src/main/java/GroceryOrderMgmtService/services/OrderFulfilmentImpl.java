package GroceryOrderMgmtService.services;

import GroceryOrderMgmtService.ValidatorFactory;
import GroceryOrderMgmtService.dao.Warehouse;
import GroceryOrderMgmtService.dao.WarehouseLocalDao;
import GroceryOrderMgmtService.dto.*;
import GroceryOrderMgmtService.enums.ValidatorRule;
import GroceryOrderMgmtService.validators.IOrderValidator;

import java.util.List;

public class OrderFulfilmentImpl implements IOrderFulfilmentService {

    public OrderResponse canFulfilOrder(OrderRequest orderRequest) {
        boolean canFulfill = checkOrderAvailability(orderRequest);
        OrderResponse response = new OrderResponse();
        response.setCanFulfil(canFulfill);

        return response;
    }

    public ReserveOrderResponse reserveOrder(OrderRequest orderRequest) {
        ReserveOrderResponse response = new ReserveOrderResponse();
        ReserveOrderData data = new ReserveOrderData();
        response.setData(data);

        boolean canFulfill = checkOrderAvailability(orderRequest);
        if(!canFulfill){
            response.setCode("Failed");
            data.setReserved(false);
            data.setMessage("Insufficient quantities!");

            return response;
        }

        Warehouse warehouse = WarehouseLocalDao.getInstance().getWarehouse(orderRequest.getWarehouseId());
        for (ItemRequest itemRequest : orderRequest.getItems()) {
            warehouse.updateItemQuantityMap(itemRequest, orderRequest.getDeliveryDate());
        }

        response.setCode("Success");
        data.setReserved(true);
        data.setMessage("Success");
        return response;
    }

    private boolean checkOrderAvailability(OrderRequest request){
        return ValidatorFactory.getInstance().getValidator(ValidatorRule.RULE1).validate(request);
    }
}
