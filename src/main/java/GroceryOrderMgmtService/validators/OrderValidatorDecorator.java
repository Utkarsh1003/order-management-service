package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dto.OrderRequest;

public abstract class OrderValidatorDecorator implements IOrderValidator{
    private IOrderValidator orderValidator;

    public OrderValidatorDecorator(IOrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    @Override
    public boolean validate(OrderRequest request) {
        return orderValidator.validate(request);
    }
}
