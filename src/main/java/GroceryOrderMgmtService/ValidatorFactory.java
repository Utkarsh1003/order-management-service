package GroceryOrderMgmtService;

import GroceryOrderMgmtService.enums.ValidatorRule;
import GroceryOrderMgmtService.validators.DefaultOrderValidator;
import GroceryOrderMgmtService.validators.IOrderValidator;
import GroceryOrderMgmtService.validators.ValidateCategoryThreshold;
import GroceryOrderMgmtService.validators.ValidateItemAvailability;

import java.util.Arrays;
import java.util.List;

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
                return new ValidateItemAvailability(new ValidateCategoryThreshold(defaultValidator));
            default:
                return defaultValidator;
        }
    }
}
