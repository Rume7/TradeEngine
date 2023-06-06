package io.turntabl.orderservice.service.validation;

import io.turntabl.orderservice.models.Order;
import io.turntabl.orderservice.models.ProductData;
import io.turntabl.orderservice.models.Side;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private Exchange exchange;

    @Override
    public boolean validateOrderForExchange1(Order order) {
        return isPriceSuitableFromExchange1(order) && isQuantityAvailableOnExchange1(order);
    }

    @Override
    public boolean validateOrderForExchange2(Order order) {
        return isPriceSuitableFromExchange2(order) && isQuantityAvailableOnExchange2(order);
    }

    private boolean isPriceSuitableFromExchange1(Order order) {
        if (order == null) return false;
        double clientPrice = order.getPrice();
        ProductData productData = exchange.getProductDataFromExchange1(order.getProduct());

        if (order.getSide() == Side.BUY) {
            return (clientPrice >= productData.ASK_PRICE()) &&
                    (clientPrice <= productData.ASK_PRICE() + productData.MAX_PRICE_SHIFT());
        } else if (order.getSide() == Side.SELL) {
            return (clientPrice <= productData.BID_PRICE()) &&
                    (clientPrice >= productData.ASK_PRICE() + productData.MAX_PRICE_SHIFT());
        }
        return false;
    }

    private boolean isPriceSuitableFromExchange2(Order order) {
        if (order == null) return false;
        double clientPrice = order.getPrice();
        ProductData productData = exchange.getProductDataFromExchange2(order.getProduct());

        if (order.getSide() == Side.BUY) {
            return (clientPrice >= productData.ASK_PRICE()) &&
                    (clientPrice <= productData.ASK_PRICE() + productData.MAX_PRICE_SHIFT());
        } else if (order.getSide() == Side.SELL) {
            return (clientPrice <= productData.BID_PRICE()) &&
                    (clientPrice >= productData.ASK_PRICE() + productData.MAX_PRICE_SHIFT());
        }
        return false;
    }

    private boolean isQuantityAvailableOnExchange1(Order order) {
        if (order == null) return false;

        long clientQty = order.getQuantity();
        ProductData productData = exchange.getProductDataFromExchange1(order.getProduct());

        if (order.getSide() == Side.BUY) {
            return clientQty <= productData.SELL_LIMIT();
        } else if (order.getSide() == Side.SELL) {
            return clientQty <= productData.BUY_LIMIT();
        }
        return false;
    }

    private boolean isQuantityAvailableOnExchange2(Order order) {
        if (order == null) return false;

        long clientQty = order.getQuantity();
        ProductData productData = exchange.getProductDataFromExchange2(order.getProduct());
        if (order.getSide() == Side.BUY) {
            return clientQty <= productData.SELL_LIMIT();
        } else if (order.getSide() == Side.SELL) {
            return clientQty <= productData.BUY_LIMIT();
        }
        return false;
    }

    private boolean canExchangesHandleBothQuantities(Order order) {
        ProductData productData1 = exchange.getProductDataFromExchange1(order.getProduct());
        ProductData productData2 = exchange.getProductDataFromExchange2(order.getProduct());

        if (order.getSide() == Side.BUY) {
            return order.getQuantity() <= (productData1.SELL_LIMIT() + productData2.SELL_LIMIT());
        } else if (order.getSide() == Side.SELL) {
            return order.getQuantity() <= (productData1.BUY_LIMIT() + productData2.BUY_LIMIT());
        }
        return false;
    }
}
