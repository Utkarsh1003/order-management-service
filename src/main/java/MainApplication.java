import GroceryOrderMgmtService.OrderResource;
import GroceryOrderMgmtService.WarehouseHealthCheck;
import GroceryOrderMgmtService.dao.CategoryThresholdLocalDao;
import GroceryOrderMgmtService.dao.ItemLocalDao;
import GroceryOrderMgmtService.dao.WarehouseLocalDao;
import GroceryOrderMgmtService.services.OrderFulfilmentImpl;
import GroceryOrderMgmtService.services.IOrderFulfilmentService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MainApplication extends Application<OrderMgmtServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    public void run(OrderMgmtServiceConfiguration configuration, Environment environment) throws Exception {
        IOrderFulfilmentService orderFulfilmentService = new OrderFulfilmentImpl();
        OrderResource orderResource = new OrderResource(orderFulfilmentService);
        WarehouseHealthCheck warehouseHealthCheck = new WarehouseHealthCheck();

        environment.jersey().register(orderResource);
        environment.jersey().register(warehouseHealthCheck);

        populateDataFromConfig(configuration);
    }

    private void populateDataFromConfig(OrderMgmtServiceConfiguration configuration) {
        ItemLocalDao.getInstance().populateData(configuration.getItems());
        WarehouseLocalDao.getInstance().populateData(configuration.getWarehouseConfig());
        CategoryThresholdLocalDao.getInstance().populateData(configuration.getCategoryThresholdConfig());
    }
}
