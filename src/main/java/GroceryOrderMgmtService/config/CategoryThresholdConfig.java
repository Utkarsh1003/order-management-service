package GroceryOrderMgmtService.config;

import GroceryOrderMgmtService.enums.ItemCategory;

import java.util.Map;

public class CategoryThresholdConfig {
    private String date;
    private Map<ItemCategory, Integer> itemCategoryThresholdMap;

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemCategoryThresholdMap(Map<ItemCategory, Integer> itemCategoryThresholdMap) {
        this.itemCategoryThresholdMap = itemCategoryThresholdMap;
    }

    public String getDate() {
        return date;
    }

    public Map<ItemCategory, Integer> getItemCategoryThresholdMap() {
        return itemCategoryThresholdMap;
    }
}
