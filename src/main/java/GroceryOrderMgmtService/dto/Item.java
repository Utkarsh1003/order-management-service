package GroceryOrderMgmtService.dto;

import GroceryOrderMgmtService.enums.ItemCategory;

public class Item {
    private String itemId;
    private String itemName;
    private ItemCategory itemCategory;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Item(String itemId, String itemName, ItemCategory itemCategory) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
    }

    public Item() {
    }
}
