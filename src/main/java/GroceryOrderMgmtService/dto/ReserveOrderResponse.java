package GroceryOrderMgmtService.dto;

public class ReserveOrderResponse {
    private String code;
    private ReserveOrderData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReserveOrderData getData() {
        return data;
    }

    public void setData(ReserveOrderData data) {
        this.data = data;
    }
}
