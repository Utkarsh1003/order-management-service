package GroceryOrderMgmtService.dto;

public class ReserveOrderData {
    private Boolean reserved;
    private String message;

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
