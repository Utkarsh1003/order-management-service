package GroceryOrderMgmtService.validators;

import GroceryOrderMgmtService.dao.CategoryThresholdDao;
import GroceryOrderMgmtService.dao.CategoryThresholdLocalDao;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.enums.ItemCategory;

import java.util.HashMap;
import java.util.Map;

public class ValidateCategoryThreshold implements OrderValidator {
    private static ValidateCategoryThreshold instance;

    public static ValidateCategoryThreshold getInstance(){
        if(instance == null)
            instance = new ValidateCategoryThreshold();

        return instance;
    }

    private CategoryThresholdDao categoryThresholdDao;
    private ValidateCategoryThreshold(){
        this.categoryThresholdDao = CategoryThresholdLocalDao.getInstance();
    }

    @Override
    public boolean validate(OrderRequest request) {
        Map<ItemCategory, Integer> itemCategoryCount = getItemCategoryCountFromReq(request);
        for (ItemCategory itemCategory : itemCategoryCount.keySet()) {
            Integer threshold = categoryThresholdDao.getLimit(request.getDeliveryDate(), itemCategory);
            if(threshold == null || itemCategoryCount.get(itemCategory) > threshold)
                return false;
        }

        return true;
    }

    private Map<ItemCategory, Integer> getItemCategoryCountFromReq(OrderRequest request) {
        Map<ItemCategory, Integer> itemCategoryCountMap = new HashMap<>();
        for (ItemRequest itemRequest : request.getItems()) {
            Integer itemCountCount = itemCategoryCountMap.getOrDefault(itemRequest.getItemCategory(), 0);
            itemCountCount += itemRequest.getQuantity();

            itemCategoryCountMap.put(itemRequest.getItemCategory(), itemCountCount);
        }
        return itemCategoryCountMap;
    }
}
