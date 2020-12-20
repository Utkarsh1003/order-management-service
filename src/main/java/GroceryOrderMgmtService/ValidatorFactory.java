package GroceryOrderMgmtService;

import GroceryOrderMgmtService.enums.ValidatorRule;
import GroceryOrderMgmtService.validators.OrderValidator;
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

    private OrderValidator availabilityValidator;
    private OrderValidator categoryThresholdValidator;

    public ValidatorFactory() {
        this.availabilityValidator = ValidateItemAvailability.getInstance();
        this.categoryThresholdValidator = ValidateCategoryThreshold.getInstance();
    }

    public List<OrderValidator> getValidators(ValidatorRule validatorRule){
        switch (validatorRule) {
            case RULE1:
                return Arrays.asList(availabilityValidator, categoryThresholdValidator);
            default:
                return Arrays.asList(availabilityValidator, categoryThresholdValidator);
        }
    }
}
