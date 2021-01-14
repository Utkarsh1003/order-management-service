package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dto.OrderRequest;

public interface IOrderValidator {
    boolean validate(OrderRequest request);
}
