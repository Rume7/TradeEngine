package io.turntabl.service.orderbook;

import io.turntabl.config.ExchangeUrlSettings;
import io.turntabl.exception.TickerNotAvailableForTrading;
import io.turntabl.exception.TradeActionNotAvailable;
import io.turntabl.models.orderbook.FullOrderBook;
import io.turntabl.models.orderbook.OrderDTO;
import io.turntabl.models.orderbook.OrderSide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    private ExchangeUrlSettings exchangeUrl;

    @Autowired
    private ExchangeTickerService exchangeTickerService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public FullOrderBook[] getOrderBook() {
        logger.info("Service: All Order books from exchange.");
        return restTemplate.getForObject(buildUrl(), FullOrderBook[].class);
    }

    public Set<String> getAllTickers() {
        return exchangeTickerService.getAllTickers();
    }

    public OrderDTO[] getOrderBookForATicker(String ticker) throws TickerNotAvailableForTrading {
        if (confirmTickerExists(ticker))
            throw new TickerNotAvailableForTrading("Ticker is currently unavailable.");

        String tickerUrl = buildUrl(ticker);
        logger.info(ticker + " order book link => " + tickerUrl);
        return restTemplate.getForObject(tickerUrl, OrderDTO[].class);
    }

    @Override
    public OrderDTO[] getOrderBookForATickerWithBuyOrSell(String ticker, String action) {
        logger.trace("Order book for " + ticker + " on a " + action + " action.");

        if (confirmTickerExists(ticker))
            throw new TickerNotAvailableForTrading("Ticker is currently unavailable.");

        if (OrderSide.BUY.name().equalsIgnoreCase(action)) {
            String tickerUrl = buildUrl(ticker, action);
            return restTemplate.getForObject(tickerUrl, OrderDTO[].class);
        } else if (OrderSide.SELL.name().equalsIgnoreCase(action)) {
            String tickerUrl = buildUrl(ticker, action);
            return restTemplate.getForObject(tickerUrl, OrderDTO[].class);
        } else {
            logger.info("Order book for " + ticker + " on a " + action + " action.");
            throw new TradeActionNotAvailable("This trade action isn't available");
        }
    }

    public List<OrderDTO> getAllExecutedOrders(String ticker, String action) {
        logger.info("All executed " + action + " orders for " + ticker);

        if (confirmTickerExists(ticker))
            throw new TickerNotAvailableForTrading("Stock is not available");

        OrderDTO[] gottenOrders = getOrderBookForATickerWithBuyOrSell(ticker, action);
        List<OrderDTO> executedOrders = new ArrayList<>();

        if (gottenOrders.length != 0) {
            for (OrderDTO order : gottenOrders) {
                if (!order.getExecutions().isEmpty()) {
                    executedOrders.add(order);
                }
            }
        }
        return executedOrders;
    }

    public OrderDTO getLastExecutedOrder(String ticker, String action) {
        logger.info("Last executed " + action + " order for " + ticker);

        if (confirmTickerExists(ticker))
            throw new TickerNotAvailableForTrading(ticker + " is not available at the moment.");

        List<OrderDTO> executedOrders = getAllExecutedOrders(ticker, action);
        if (executedOrders.size() > 0) {
            return executedOrders.get(executedOrders.size() - 1);
        }
        return new OrderDTO();
    }

    private boolean confirmTickerExists(String ticker) {
        return exchangeTickerService.validateTicker(ticker);
    }

    private String buildUrl(String... args) {
        String url = exchangeUrl.getExchange1();
        StringBuilder finalUrl = new StringBuilder(url);
        for (String arg : args) {
            finalUrl.append("/").append(arg);
        }
        return finalUrl.toString();
    }
}
