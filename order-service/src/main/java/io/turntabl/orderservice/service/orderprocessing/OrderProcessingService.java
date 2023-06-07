package io.turntabl.orderservice.service.orderprocessing;

import io.turntabl.orderservice.config.ExchangeConfig;
import io.turntabl.orderservice.models.Order;
import io.turntabl.orderservice.models.OrderDTO;
import io.turntabl.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderProcessingService {

    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeConfig exchangeConfig;

    @Autowired
    public OrderProcessingService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public Order sendOrderToExchange(Order order) {
        ResponseEntity<Object> response = executeOnExchange(order);
        order.setOrderId(response.getBody().toString());
        Order gottenOrder = orderRepository.save(order);
        System.out.println(gottenOrder);
        return gottenOrder;
    }

    // TODO:
    void multiLegStrategy(Order order) {

    }

    private ResponseEntity<Object> executeOnExchange(Order order) {
        String urlLink = buildOrderURL();
        OrderDTO orderDTO = new OrderDTO(order.getProduct(), order.getQuantity(), order.getPrice(),
                order.getSide(), order.getType());
        ResponseEntity<Object> object = restTemplate.postForEntity(urlLink, orderDTO, Object.class);
        return object;
    }

    public Order getAnOrder(UUID id) {
        Optional<Order> anOrder = orderRepository.findById(id);
        return anOrder.orElse(null);
    }

    private String buildOrderURL() {
        StringBuilder builder = new StringBuilder(exchangeConfig.getExchange1Url());
        builder.append("/").append(exchangeConfig.getApiKey()).append("/").append("order");
        return builder.toString();
    }
}
