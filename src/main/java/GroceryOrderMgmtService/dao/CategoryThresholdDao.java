package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.enums.ItemCategory;

import java.util.Date;

public interface CategoryThresholdDao {
    Integer getLimit(Date date, ItemCategory itemCategory);
}
