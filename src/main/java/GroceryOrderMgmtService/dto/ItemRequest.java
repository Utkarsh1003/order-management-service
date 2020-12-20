package GroceryOrderMgmtService.dto;

import GroceryOrderMgmtService.enums.ItemCategory;

public class ItemRequest extends Item {
    private Integer quantity;

    public ItemRequest() {
    }

    public ItemRequest(String itemId, String itemName, ItemCategory itemCategory) {
        super(itemId, itemName, itemCategory);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
