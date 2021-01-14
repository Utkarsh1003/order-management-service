package GroceryOrderMgmtService.validators.orderValidationDecorators;

import GroceryOrderMgmtService.dao.CategoryThresholdDao;
import GroceryOrderMgmtService.dao.CategoryThresholdLocalDao;
import GroceryOrderMgmtService.dto.Item;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.enums.ItemCategory;
import GroceryOrderMgmtService.validators.IOrderValidator;

import java.util.Map;
import java.util.stream.Collectors;

public class CategoryThresholdDecorator extends OrderValidationDecorator {
    private CategoryThresholdDao categoryThresholdDao;
    public CategoryThresholdDecorator(IOrderValidator orderValidator){
        super(orderValidator);
        this.categoryThresholdDao = CategoryThresholdLocalDao.getInstance();
    }

    @Override
    public boolean validate(OrderRequest request) {
        if(!decoratedOrderValidator.validate(request))
            return false;

        Map<ItemCategory, Integer> itemCategoryCount = request.getItems().stream()
                .collect(Collectors.groupingBy(Item::getItemCategory, Collectors.summingInt(ItemRequest::getQuantity)));

        for (ItemCategory itemCategory : itemCategoryCount.keySet()) {
            Integer threshold = categoryThresholdDao.getLimit(request.getDeliveryDate(), itemCategory);
            if(threshold == null || itemCategoryCount.get(itemCategory) >= threshold)
                return false;
        }

        return true;
    }
}
