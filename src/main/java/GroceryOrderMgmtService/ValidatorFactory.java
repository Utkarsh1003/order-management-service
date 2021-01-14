package GroceryOrderMgmtService;

import GroceryOrderMgmtService.enums.ValidatorRule;
import GroceryOrderMgmtService.validators.DefaultOrderValidator;
import GroceryOrderMgmtService.validators.IOrderValidator;
import GroceryOrderMgmtService.validators.orderValidationDecorators.CategoryThresholdDecorator;
import GroceryOrderMgmtService.validators.orderValidationDecorators.ItemAvailabilityDecorator;

public class ValidatorFactory {
    private static ValidatorFactory instance;

    public static ValidatorFactory getInstance(){
        if(instance == null)
            instance = new ValidatorFactory();

        return instance;
    }

    private IOrderValidator defaultValidator;

    private ValidatorFactory() {
        this.defaultValidator = DefaultOrderValidator.getInstance();
    }

    public IOrderValidator getValidator(ValidatorRule validatorRule){
        switch (validatorRule) {
            case RULE1:
                return new ItemAvailabilityDecorator(new CategoryThresholdDecorator(defaultValidator));
            default:
                return defaultValidator;
        }
    }
}
