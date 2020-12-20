package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dto.OrderRequest;

public interface OrderValidator {
    boolean validate(OrderRequest request);
}
