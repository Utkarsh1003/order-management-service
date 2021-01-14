package GroceryOrderMgmtService.validators.orderValidationDecorators;

import GroceryOrderMgmtService.validators.IOrderValidator;

public abstract class OrderValidationDecorator implements IOrderValidator {
    protected IOrderValidator decoratedOrderValidator;

    public OrderValidationDecorator(IOrderValidator orderValidator) {
        this.decoratedOrderValidator = orderValidator;
    }
}
