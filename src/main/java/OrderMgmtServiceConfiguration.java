import GroceryOrderMgmtService.config.CategoryThresholdConfig;
import GroceryOrderMgmtService.config.WarehouseConfig;
import GroceryOrderMgmtService.dto.Item;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

public class OrderMgmtServiceConfiguration extends Configuration {
    @NotEmpty
    private List<Item> items;
    @NotEmpty
    private Map<String, List<WarehouseConfig>> warehouseConfig;
    @NotEmpty
    private List<CategoryThresholdConfig> categoryThresholdConfig;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, List<WarehouseConfig>> getWarehouseConfig() {
        return warehouseConfig;
    }

    public void setWarehouseConfig(Map<String, List<WarehouseConfig>> warehouseConfig) {
        this.warehouseConfig = warehouseConfig;
    }

    public List<CategoryThresholdConfig> getCategoryThresholdConfig() {
        return categoryThresholdConfig;
    }

    public void setCategoryThresholdConfig(List<CategoryThresholdConfig> categoryThresholdConfig) {
        this.categoryThresholdConfig = categoryThresholdConfig;
    }
}
