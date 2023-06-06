package io.turntabl.orderservice.service.validation;

import io.turntabl.orderservice.models.Order;

public interface ExchangeService {

    boolean validateOrderForExchange1(Order order);

    boolean validateOrderForExchange2(Order order);
}