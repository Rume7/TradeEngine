package io.turntabl.models.orderbook;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonSerialize
public class OrderDTO implements Serializable {

    private String product;
    private int quantity;
    private double price;
    private OrderSide side;
    private List<Execution> executions;
    private String orderID;
    private OrderType orderType;
    private long cumulatitiveQuantity;
    private double cumulatitivePrice;

    public OrderDTO() {
        this.executions = new ArrayList<>();
    }

    public OrderDTO(String product, int quantity, double price, OrderSide side, String orderID,
                    OrderType orderType, long cumulatitiveQuantity, double cumulatitivePrice,
                    List<Execution> executions) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.orderID = orderID;
        this.orderType = orderType;
        this.cumulatitiveQuantity = cumulatitiveQuantity;
        this.cumulatitivePrice = cumulatitivePrice;
        this.executions = executions;
    }
}
