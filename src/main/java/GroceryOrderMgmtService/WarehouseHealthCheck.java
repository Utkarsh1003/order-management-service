package GroceryOrderMgmtService;

import com.codahale.metrics.health.HealthCheck;

public class WarehouseHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
