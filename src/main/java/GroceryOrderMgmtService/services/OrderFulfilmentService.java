package GroceryOrderMgmtService.services;

import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.dto.OrderResponse;
import GroceryOrderMgmtService.dto.ReserveOrderResponse;

public interface OrderFulfilmentService {
    OrderResponse canFulfilOrder(OrderRequest orderRequest);
    ReserveOrderResponse reserveOrder(OrderRequest orderRequest);
}
