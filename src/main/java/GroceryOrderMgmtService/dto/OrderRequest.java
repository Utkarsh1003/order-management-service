package GroceryOrderMgmtService.dto;


import GroceryOrderMgmtService.IConstants;

import javax.validation.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderRequest {
    private String customerId;
    private String warehouseId;
    private Date deliveryDate;
    private List<ItemRequest> items;

    public OrderRequest() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        try {
            this.deliveryDate = new SimpleDateFormat(IConstants.dateParserPattern).parse(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ValidationException("Invalid date");
        }
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }
}
