package io.turntabl.orderservice.service.validation;

import io.turntabl.orderservice.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderValidatorService {

    @Autowired
    private ExchangeService exchangeService;

    public boolean validateOrder(Order order) {
        return validateOrderForExchange1(order) || validateOrderForExchange2(order);
    }

    private boolean validateOrderForExchange1(Order order) {
        return exchangeService.validateOrderForExchange1(order);
    }

    private boolean validateOrderForExchange2(Order order) {
        return exchangeService.validateOrderForExchange2(order);
    }

    public void cancelOrder(String orderId) {
        // check status of the order. if not executed, and order is a LIMIT, delete.

        // If order is a MARKET, close the order.
        // If executed, inform client and cancel at a loss
    }
}
