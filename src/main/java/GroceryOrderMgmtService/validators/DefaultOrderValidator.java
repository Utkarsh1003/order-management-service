package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dto.OrderRequest;

public class DefaultOrderValidator implements IOrderValidator{
    private static DefaultOrderValidator instance = new DefaultOrderValidator();

    public static DefaultOrderValidator getInstance() {
        return instance;
    }

    private DefaultOrderValidator() {
    }

    @Override
    public boolean validate(OrderRequest request) {
        //Some default validation of the request payload
        return request != null && request.getItems() != null && !request.getItems().isEmpty();
    }
}
