import GroceryOrderMgmtService.config.CategoryThresholdConfig;
import GroceryOrderMgmtService.dao.CategoryThresholdLocalDao;
import GroceryOrderMgmtService.dto.ItemRequest;
import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.enums.ItemCategory;
import GroceryOrderMgmtService.validators.DefaultOrderValidator;
import GroceryOrderMgmtService.validators.orderValidationDecorators.CategoryThresholdDecorator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValidateCategoryThresholdTest {

    @Test
    public void testCategoryThresholdValidation(){
        Map<ItemCategory, Integer> itemCategoryCountMap = new HashMap<>();
        itemCategoryCountMap.put(ItemCategory.F_N_V, 20);
        CategoryThresholdConfig config = new CategoryThresholdConfig();
        config.setDate("01-01-2020");
        config.setItemCategoryThresholdMap(itemCategoryCountMap);

        CategoryThresholdLocalDao.getInstance().populateData(Collections.singletonList(config));
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerId("user1");
        orderRequest.setWarehouseId("warehouse_1");
        orderRequest.setDeliveryDate("01-01-2020");

        ItemRequest itemRequest = new ItemRequest("item_1", "Apple", ItemCategory.F_N_V);
        itemRequest.setQuantity(10);

        orderRequest.setItems(Collections.singletonList(itemRequest));

        boolean validate = new CategoryThresholdDecorator(DefaultOrderValidator.getInstance()).validate(orderRequest);
        Assert.assertTrue(validate);
    }
}
