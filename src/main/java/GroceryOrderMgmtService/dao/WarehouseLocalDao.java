package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.Constants;
import GroceryOrderMgmtService.config.WarehouseConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseLocalDao implements WarehouseDao{
    private static WarehouseLocalDao instance;
    public static WarehouseLocalDao getInstance(){
        if(instance == null)
            instance = new WarehouseLocalDao();

        return instance;
    }

    private Map<String, Warehouse> warehouseMap;
    private WarehouseLocalDao(){
        this.warehouseMap = new HashMap<>();
    }

    @Override
    public Warehouse getWarehouse(String id) {
        return warehouseMap.get(id);
    }

    @Override
    public Integer getItemQuantity(String warehouseId, Date date, String itemId) {
        Warehouse warehouse = warehouseMap.get(warehouseId);
        return warehouse.getItemQuantity(date, itemId);
    }

    public void populateData(Map<String, List<WarehouseConfig>> warehouseConfig){
        for (String warehouseId : warehouseConfig.keySet()) {
            Warehouse warehouse = new Warehouse();
            warehouseConfig.get(warehouseId).forEach(warehouseDayWiseData -> {
                String dateString = warehouseDayWiseData.getDate();
                try {
                    Date date = new SimpleDateFormat(Constants.dateParserPattern).parse(dateString);
                    warehouse.populateItemQuantityMap(date, warehouseDayWiseData.getItemQuantityMap());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            warehouseMap.put(warehouseId, warehouse);
        }
    }
}
