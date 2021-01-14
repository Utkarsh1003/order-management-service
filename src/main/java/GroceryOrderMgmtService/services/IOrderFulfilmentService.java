package GroceryOrderMgmtService.services;

import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.dto.OrderResponse;
import GroceryOrderMgmtService.dto.ReserveOrderResponse;

public interface IOrderFulfilmentService {
    OrderResponse canFulfilOrder(OrderRequest orderRequest);
    ReserveOrderResponse reserveOrder(OrderRequest orderRequest);
}
